package com.newwave.bu3internecommerce.repository;

import com.newwave.bu3internecommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
