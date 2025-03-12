package com.newwave.bu3internecommerce.repository;

import com.newwave.bu3internecommerce.model.shoppingcart.Cart;
import com.newwave.bu3internecommerce.model.shoppingcart.CartItem;
import com.newwave.bu3internecommerce.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartAndLaptop(Cart cart, Laptop laptop);

    List<CartItem> findByCart(Cart cart);
}
