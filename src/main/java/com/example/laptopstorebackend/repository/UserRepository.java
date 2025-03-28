package com.example.laptopstorebackend.repository;

import com.example.laptopstorebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
