package com.example.laptopstorebackend.repository;

import com.example.laptopstorebackend.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends org.springframework.data.jpa.repository.JpaRepository<User, Long> {
}
