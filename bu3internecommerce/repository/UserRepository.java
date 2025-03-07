package com.newwave.bu3internecommerce.repository;

import com.newwave.bu3internecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserName(String userName);
}
