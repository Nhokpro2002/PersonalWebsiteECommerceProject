package com.newwave.bu3internecommerce.service;

import com.newwave.bu3internecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    @Autowired
    CartRepository cartRepository;

   /* public List<Product> addProduct(Product product) {
        cartRepository.save(product);
        return
    }*/
}
