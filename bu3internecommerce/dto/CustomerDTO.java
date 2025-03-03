package com.newwave.bu3internecommerce.dto;

import com.newwave.bu3internecommerce.model.Customer;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
public class CustomerDTO {

        private String userName;
        private String email;

        public CustomerDTO(Customer customer) {
            this.userName = customer.getUserName();
            this.email = customer.getEmail();
        }

}
