package com.groupjn.cartservice.memento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private int id;
    private int productId;
    private int userId;
    private int qty;
    private double price;

    public CartItem(int id, int productId, int userId, double price,int qty){
        this.productId = productId;
        this.userId = userId;
        this.qty = qty;
        this.price = price;
    }

    public CartItem(int productId){
        this.productId =  productId;
    }


    public CartItem Clone()
    {
        return new CartItem(id)
        {
            @Override
            public void setUserId(int userId) {
                super.setUserId(userId);
            }

            @Override
            public void setQty(int qty) {
                super.setQty(qty);
            }

            @Override
            public void setPrice(double price) {
                super.setPrice(price);
            }
        };
    }
}
