package com.groupjn.cartservice.memento;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCart {

    private List<CartItem> _cartItems = new ArrayList<CartItem>();
    public int id;

    public ShoppingCart(int id)
    {
        this.id = id;
    }

    public CartItem getCartItem(int productId){
        CartItem cartItem = new CartItem();
        for(CartItem cart : _cartItems){
            if(cart.getProductId() == productId){
                cartItem = cart;
                break;
            }
        }
        return cartItem;
    }


    public void AddItem(CartItem cartItem)
    {
        _cartItems.add(cartItem);
    }

    public void RemoveItem(CartItem cartItem)
    {
        _cartItems.remove(cartItem);
    }

    public void Restore(List<CartItem> cartItems)
    {
        _cartItems = cartItems;
    }
}
