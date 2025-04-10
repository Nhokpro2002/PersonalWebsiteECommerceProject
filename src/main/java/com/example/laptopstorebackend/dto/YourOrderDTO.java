package com.example.laptopstorebackend.dto;

import com.example.laptopstorebackend.constant.YourOrderStatus;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YourOrderDTO {
    private Long yourOrderId;
    private String customerName;
    private String customerAddress;
    private String agentName;
    private List<YourOrderItemDTO> items;

    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal totalPrice;

    @JsonSerialize(using = ToStringSerializer.class)
    private YourOrderStatus yourOrderStatus;
}
