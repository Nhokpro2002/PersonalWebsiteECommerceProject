package com.example.laptopstorebackend.repository;

import com.example.laptopstorebackend.entity.YourOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YourOrderRepository extends JpaRepository<Long, YourOrder> {
}
