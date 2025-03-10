package com.newwave.bu3internecommerce.repository;

import com.newwave.bu3internecommerce.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserName(String userName);

    Page<User> findAll(Pageable pageable);

    Optional<User> findByUserName(String userName);
}
