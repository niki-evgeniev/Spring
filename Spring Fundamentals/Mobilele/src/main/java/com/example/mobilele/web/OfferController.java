package com.example.mobilele.web;

import com.example.mobilele.model.DTO.OfferAddBindingModel;
import com.example.mobilele.model.DTO.OfferDetailsBindingModel;
import com.example.mobilele.services.BrandService;
import com.example.mobilele.services.OfferService;
import com.example.mobilele.services.exception.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    private final BrandService brandService;

    public OfferController(OfferService offerService, BrandService brandService) {
        this.offerService = offerService;
        this.brandService = brandService;
    }

//    @ModelAttribute("engines")
//    public EngineType[] engines() {
//        return EngineType.values();
//    }


    @GetMapping("/add")
    public ModelAndView add() {

        ModelAndView modelAndView = new ModelAndView("offer-add");
        modelAndView.addObject("brands", brandService.getAllBrand());

        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView modelAndView(@Valid OfferAddBindingModel offerAddBindingModel,
                                     BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            boolean offerIsAdded = offerService.addOffer(offerAddBindingModel);
            if (offerIsAdded){
                return new ModelAndView("details");
            }
        }

        return new ModelAndView("offer-add");
    }

    @ModelAttribute OfferAddBindingModel offerAddBindingModel (){
        return new OfferAddBindingModel();
    }


//    @GetMapping("/all")
//    public ModelAndView all(){
//        return new ModelAndView("offers");
//    }

    @GetMapping("/{id}")
    public String details(@PathVariable ("id") String id, Model model) {

        OfferDetailsBindingModel offerDetails= offerService.getOfferDetails(id)
                .orElseThrow(() -> new ObjectNotFoundException("Offer details not found"));

        model.addAttribute("offerDetails", offerDetails);

        return "details";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id){

        offerService.deleteOffer(id);

        return "redirect:/offers/all";

    }

}
