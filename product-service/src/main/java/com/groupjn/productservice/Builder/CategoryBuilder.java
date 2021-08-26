package com.groupjn.productservice.Builder;

import lombok.Data;

@Data
public class CategoryBuilder
{
    private String name;
    private String description;

    public CategoryBuilder(String name) {
        this.name = name;
    }


    public CategoryBuilder description(String description) {
        this.description = description;
        return this;
    }


    public CategoryClass build() {

        return  new CategoryClass(this);
    }


    private void validateUserObject(CategoryClass user) {
        //Do some basic validations to check
        //if user object does not break any assumption of system
    }
}