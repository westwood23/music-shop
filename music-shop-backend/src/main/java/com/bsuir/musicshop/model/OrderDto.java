package com.bsuir.musicshop.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Integer orderId;
    private LocalDate orderDate;
    private BigDecimal orderCost;
}