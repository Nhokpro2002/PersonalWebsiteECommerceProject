package com.newwave.bu3internecommerce.repository;

import com.newwave.bu3internecommerce.entity.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    Orders findByCartId(Long cartId);
}
