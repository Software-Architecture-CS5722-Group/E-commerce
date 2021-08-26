package com.groupjn.productservice.Builder;

import lombok.Data;

@Data
public class CategoryClass {
    //All final attributes
    private final String name; // required
    private final String description; // required

    public CategoryClass(CategoryBuilder ct) {
        this.name = ct.getName();
        this.description = ct.getDescription();
    }


}
