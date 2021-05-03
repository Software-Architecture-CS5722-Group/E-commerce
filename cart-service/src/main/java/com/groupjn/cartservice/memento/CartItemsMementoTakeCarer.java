package com.groupjn.cartservice.memento;

import java.util.ArrayList;
import java.util.List;

public class CartItemsMementoTakeCarer implements IMementoCareTaker<List<CartItem>> {

    private  List<CartItem> _memento = new ArrayList<>();

    public CartItemsMementoTakeCarer(List<CartItem> cartItems)
    {
        //_memento = new List<CartItem>();

        for(CartItem cartItem : cartItems)
        {
            _memento.add(cartItem.Clone());
        }
    }

    public List<CartItem> Memento()
    {
            return _memento;
    }
}
