package com.example.laptopstorebackend.service;

import com.example.laptopstorebackend.entity.YourOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class YourOrderServiceImpl extends GenericServiceImpl<YourOrder, Long> {

    public YourOrderServiceImpl(JpaRepository<YourOrder, Long> repository) {
        super(repository);
    }
}
