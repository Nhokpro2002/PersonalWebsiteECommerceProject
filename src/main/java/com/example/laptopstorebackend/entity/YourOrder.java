package com.example.laptopstorebackend.entity;

import com.example.laptopstorebackend.constant.YourOrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    private YourOrderStatus yourOrderStatus;

    @Column(name = "yourOrder_totalPrice", nullable = false)
    private BigDecimal totalPrice;

    private LocalDate createAt;

    @OneToMany(mappedBy = "yourOrder", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<YourOrderItem> yourOrderItemsItems;

}
