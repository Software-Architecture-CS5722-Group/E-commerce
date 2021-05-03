package com.groupjn.cartservice.vao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductToCart {
    private int productId;
    private int userId;
    private int qty;
    private double price;
}
