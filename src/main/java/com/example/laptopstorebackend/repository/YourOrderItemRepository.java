package com.example.laptopstorebackend.repository;

import com.example.laptopstorebackend.entity.YourOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YourOrderItemRepository extends JpaRepository<YourOrderItem, Long> {
}
