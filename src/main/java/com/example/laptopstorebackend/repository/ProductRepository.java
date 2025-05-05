package com.example.laptopstorebackend.repository;

import com.example.laptopstorebackend.constant.Category;
import com.example.laptopstorebackend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Object> findByProductName(String productName);

    @Override
    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllByCategory(Category category, Pageable pageable);

    Integer countByCategory(Category category);

}
