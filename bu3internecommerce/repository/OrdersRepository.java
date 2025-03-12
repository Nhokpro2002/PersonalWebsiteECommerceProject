package com.newwave.bu3internecommerce.repository;

import com.newwave.bu3internecommerce.model.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    Orders findByCartId(Long cartId);
}
