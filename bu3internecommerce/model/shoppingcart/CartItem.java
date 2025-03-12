package com.newwave.bu3internecommerce.model.shoppingcart;


import com.newwave.bu3internecommerce.model.Laptop;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "laptop_id", nullable = false)
    private Laptop laptop;

    @Column(nullable = false)
    private int quantity;

    public CartItem(Cart cart, Laptop laptop, int quantity) {
        this.cart = cart;
        this.laptop = laptop;
        this.quantity = quantity;
    }

    public CartItem() {

    }
}
