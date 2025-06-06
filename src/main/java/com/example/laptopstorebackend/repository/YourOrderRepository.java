package com.example.laptopstorebackend.repository;

import com.example.laptopstorebackend.entity.YourOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YourOrderRepository extends JpaRepository<YourOrder, Long> {
}
