package com.example.mobilele.web;

import com.example.mobilele.model.DTO.UserLoginBindingModel;
import com.example.mobilele.model.DTO.UserRegisterBindingModel;
import com.example.mobilele.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {


    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("/login-error")
    public ModelAndView errorLogin() {

        ModelAndView modelAndView = new ModelAndView("auth-login");
        modelAndView.addObject("bad_credentials", true);

        return modelAndView;
    }

    @GetMapping("/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            boolean isRegisterComplete = userServices.register(userRegisterBindingModel);

            if (isRegisterComplete) {
                return new ModelAndView("auth-login");
            }
        }

        return new ModelAndView("/auth-register");
    }


    @ModelAttribute
    UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute
    UserLoginBindingModel userLoginBindingModel(){
        return new UserLoginBindingModel();
    }


}
