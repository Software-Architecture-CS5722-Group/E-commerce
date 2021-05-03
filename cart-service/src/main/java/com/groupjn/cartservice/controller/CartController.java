package com.groupjn.cartservice.controller;


import com.groupjn.cartservice.configuration.ShoppingConfiguration;
import com.groupjn.cartservice.entity.Cart;
import com.groupjn.cartservice.memento.CartItem;
import com.groupjn.cartservice.memento.ShoppingCart;
import com.groupjn.cartservice.memento.ShoppingCartChangeTracker;
import com.groupjn.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;

    ShoppingCart shoppingCart = new ShoppingCart(8973);
    ShoppingCartChangeTracker shoppingCartTracker = new ShoppingCartChangeTracker(shoppingCart);


    @PostMapping("addProductToCart")
    public ResponseEntity<?> addProductToCart(@RequestBody CartItem addCartRequest){
       try {
           List<Cart> cart =  cartService.addCartbyUserIdAndProductId(addCartRequest);
           shoppingCart.AddItem(addCartRequest);
           shoppingCartTracker.Save();
           writeLine(shoppingCart);
           System.out.println(shoppingCart);
           return ResponseEntity.ok(cart);
       }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Product with id: "+addCartRequest.getProductId()+" already exist in cart");
       }
    }


    @PostMapping("removeProductFromCart")
    public ResponseEntity<?> removeCartWithProductId(@RequestBody HashMap<String, String> removeCartRequest) throws Exception {

        try{
            String keys[] = {"userId","cartId"};
            if(ShoppingConfiguration.validationWithHashMap(keys,removeCartRequest)){

            }
            List<Cart> cart =  cartService.removeCartByUserId(Integer.parseInt(removeCartRequest.get("cartId")),
                    Integer.parseInt(removeCartRequest.get("userId")));
            shoppingCart.Restore(convertCartToCartListItems(cart));
            shoppingCart.RemoveItem(shoppingCart.getCartItem(Integer.parseInt(removeCartRequest.get("cartId"))));
            shoppingCartTracker.Save();
            writeLine(shoppingCart);
            System.out.println(shoppingCart);
            return ResponseEntity.ok(cart);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error procesing request");
        }


    }

    @PostMapping("getCartByUserId")
    public ResponseEntity<?> getCartsByUserId(@RequestBody HashMap<String, String> getCartRequest){
        try{
            String keys[] = {"userId"};
            if(ShoppingConfiguration.validationWithHashMap(keys,getCartRequest)){

            }
            List<com.groupjn.cartservice.entity.Cart> cart =  cartService.getCartByUserId(Integer.parseInt(getCartRequest.get("userId")));
            return ResponseEntity.ok(cart);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error procesing request");
        }
    }



    @PostMapping("updateQtyForCart")
    public ResponseEntity<?> updateQtyForCart(@RequestBody HashMap<String, String> updateCartRequest){

        try {
            String keys[] = {"cartId","userId","qty","price"};
            if(ShoppingConfiguration.validationWithHashMap(keys,updateCartRequest)){

            }

            int cartId = Integer.parseInt(updateCartRequest.get("cartId"));
            int userId = Integer.parseInt(updateCartRequest.get("userId"));
            int quantity = Integer.parseInt(updateCartRequest.get("qty"));
            double price = Double.parseDouble(updateCartRequest.get("price"));
            cartService.updateQtyByCartId(cartId,price,quantity);
            List<Cart> cart =  cartService.getCartByUserId(userId);
            shoppingCart.Restore(convertCartToCartListItems(cart));
            shoppingCartTracker.Undo();
            System.out.println(shoppingCart);
            return ResponseEntity.ok(cart);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Bad request");
        }
    }





    public List<CartItem> convertCartToCartListItems(List<Cart> cart){
        List<CartItem> ci = new ArrayList<>();
        for(Cart cartItem: cart){
            ci.add(new CartItem(
                    cartItem.getId(),
                    cartItem.getProduct_id(),
                    cartItem.getUser_id(),
                    cartItem.getPrice(),
                    cartItem.getQuantity())
            );
        }
        return ci;
    }

    public CartItem convertCartToCartItems(Cart cart){
        return new CartItem(
                cart.getId(),
                cart.getProduct_id(),
                cart.getUser_id(),
                cart.getPrice(),
                cart.getQuantity());
    }

    public void writeLine(ShoppingCart shoppingCart)
    {
        for(CartItem cartItem: shoppingCart.get_cartItems()){
            System.out.println("Price: "+ cartItem.getPrice()+", Product Id: "+cartItem.getProductId()+", Quantity: "+cartItem.getQty()+", User Id: "+cartItem.getUserId());
        }
    }


}
