package com.groupjn.cartservice.service;

import com.groupjn.cartservice.entity.Cart;
import com.groupjn.cartservice.memento.CartItem;
import com.groupjn.cartservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> addCartbyUserIdAndProductId(CartItem productToCart) throws Exception{

        try {
            if(cartRepository.getCartByproductIdAnduserId(productToCart.getUserId(),productToCart.getProductId()).isPresent()){
                throw new Exception("Product is aready in cart.");
            }

            Cart cart = new Cart();
            cart.setQuantity(productToCart.getQty());
            cart.setUser_id(productToCart.getUserId());
            cart.setProduct_id(productToCart.getProductId());
            //TODO price has to be checked with quantity
            cart.setPrice(productToCart.getPrice());
            cart = cartRepository.save(cart);
            return cartRepository.getCartByuserId(cart.getUser_id());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public void updateQtyByCartId(int cartId, double price, int qty) throws Exception {
        cartRepository.updateQtyByCartId(cartId,price,qty);
        cartRepository.flush();
    }

    @Override
    public List<Cart> getCartByUserId(int userId) {
        return cartRepository.getCartByuserId(userId);
    }

    @Override
    public List<Cart> removeCartByUserId(int id, int userId) {
        cartRepository.deleteAllCartByIdUserId(id,userId);
        cartRepository.flush();
        return this.getCartByUserId(userId);
    }
}
