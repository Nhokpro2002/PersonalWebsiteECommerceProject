package com.newwave.bu3internecommerce.repository;

import com.newwave.bu3internecommerce.model.shoppingcart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
