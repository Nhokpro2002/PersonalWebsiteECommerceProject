package com.newwave.bu3internecommerce.controller;


import com.newwave.bu3internecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    //@GetMapping("/list")
   /* public List<Product> showProductList() {

        customerService.
    }*/
}
