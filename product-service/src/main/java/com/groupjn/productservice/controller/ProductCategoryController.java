package com.groupjn.productservice.controller;

import com.groupjn.productservice.Builder.CategoryBuilder;
import com.groupjn.productservice.Builder.CategoryClass;
import com.groupjn.productservice.api.command.AddProductCommand;
import com.groupjn.productservice.entity.ProductSummary;
import com.groupjn.productservice.model.Category;
import com.groupjn.productservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("product/category")
public class ProductCategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/addCategory")
    public Category addCategory(@RequestBody String name, @RequestBody String description) {
        CategoryClass category  = new CategoryBuilder(name).description(description).build();
        return categoryRepository.save(new Category(category.getName(),category.getDescription()));
    }


}
