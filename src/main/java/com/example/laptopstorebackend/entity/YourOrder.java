package com.example.laptopstorebackend.entity;

import com.example.laptopstorebackend.constant.YourOrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "your_order")
public class YourOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long yourOrderId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "Agent_name")
    private String saleAgent;

    @Enumerated(EnumType.STRING)
    private YourOrderStatus yourOrderStatus;

    @Column(name = "yourOrder_totalPrice")
    private BigDecimal totalPrice;

    private LocalDate createAt;

}
