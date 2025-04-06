package com.example.laptopstorebackend.repository;

import com.example.laptopstorebackend.entity.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

    Optional<ShoppingCartItem> findByShoppingCartIdAndProductId(Long shoppingCartId, Long productId);

    List<ShoppingCartItem> findByShoppingCartId(Long shoppingCartId);

    ShoppingCartItem deleteByShoppingCartIdAndProductId(Long shoppingCartId, Long productId);
}
