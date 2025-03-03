package com.newwave.bu3internecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "isAdmin")
    private boolean isAdmin;

    @OneToOne(cascade = CascadeType.ALL) // Khi xóa Customer, Cart cũng bị xóa
    @JoinColumn(name = "cart_id", referencedColumnName = "id", unique = true)
    private Cart cart;

    @Override
    public String toString() {
        return "Customer: " + userName;
    }


}
