package com.newwave.bu3internecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Laptop> products = new ArrayList<>();

    public void addProduct(Laptop product) {
        products.add(product);
        product.setCart(this); // Cập nhật cart cho product
    }

    public void removeProduct(Laptop product) {
        products.remove(product); // delete product from cart
        product.setCart(null);
    }




}