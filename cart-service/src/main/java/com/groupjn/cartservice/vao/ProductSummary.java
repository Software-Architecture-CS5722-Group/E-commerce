package com.groupjn.cartservice.vao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSummary {

    private String  id;
    private Double price;
    private Integer stock;
    private String description;

}
