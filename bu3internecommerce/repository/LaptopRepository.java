package com.newwave.bu3internecommerce.repository;

import com.newwave.bu3internecommerce.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {

    Optional<Laptop> findByName(String laptopName);
}
