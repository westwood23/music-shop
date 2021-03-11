package com.bsuir.musicshop.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Integer orderId;
    private LocalDate orderDate;
    private BigDecimal orderCost;
    private Boolean isPaid;
}