package com.example.coffeeshop.service;

import com.example.coffeeshop.model.entity.Category;
import com.example.coffeeshop.model.enums.CategoryNameEnum;

public interface CategoryService {
    void initDataBaseCategories();

    Category findByCategoryName(CategoryNameEnum category);
}
