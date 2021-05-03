package com.groupjn.cartservice.repository;

import com.groupjn.cartservice.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("SELECT cart FROM Cart cart where cart.user_id=:user_id")
    List<Cart> getCartByuserId(@Param("user_id")int user_id);

    @Query("SELECT cart FROM Cart cart where cart.user_id=:user_id and cart.product_id=:product_id")
    Optional<Cart> getCartByproductIdAnduserId(@Param("user_id")int user_id,@Param("product_id")int product_id);

    @Modifying
    @Transactional
    @Query("DELETE FROM  Cart cart where cart.user_id=:user_id and cart.id=:cart_id")
    void deleteAllCartByIdUserId(@Param("user_id")int user_id, @Param("cart_id")int cart_id);


    @Modifying
    @Transactional
    @Query("DELETE FROM Cart cart where cart.user_id=:user_id")
    void deleteAllCartUserId(@Param("user_id")int user_id);

    @Modifying
    @Transactional
    @Query("UPDATE Cart cart set cart.quantity=:qty,cart.price=:price WHERE cart.id=:cart_id")
    void updateQtyByCartId(@Param("cart_id")int cart_id, @Param("price") double price, @Param("qty") Integer qty);



}
