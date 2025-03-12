package com.newwave.bu3internecommerce.model.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.newwave.bu3internecommerce.model.Laptop;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private Orders orders;


    @ManyToOne
    @JoinColumn(name = "laptop_id", nullable = false)
    private Laptop laptop;

    @Column(nullable = false)
    private int quantity;

    public OrderItem(Orders orders, Laptop laptop, int quantity) {
        this.orders = orders;
        this.laptop = laptop;
        this.quantity = quantity;
    }

    public OrderItem() {

    }
}
