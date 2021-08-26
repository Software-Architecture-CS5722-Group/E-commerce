package com.groupjn.cartservice.memento;

import java.util.List;
import java.util.Stack;

public class ShoppingCartChangeTracker {

    private  ShoppingCart _shoppingCart;
    private  Stack<IMementoCareTaker<List<CartItem>>> _stack =
            new Stack<IMementoCareTaker<List<CartItem>>>();

    public ShoppingCartChangeTracker(ShoppingCart shoppingCart)
    {
        _shoppingCart = shoppingCart;
    }

    public void Save()
    {
        _stack.push(new CartItemsMementoTakeCarer(_shoppingCart.get_cartItems()));
    }

    public void Undo()
    {
        if (!_stack.empty())
        {
            _shoppingCart.Restore(_stack.pop().Memento());
        }
    }
}
