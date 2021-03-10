package com.bsuir.musicshop.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Integer orderId;

    private LocalDate orderDate;

    private List<Item> itemsList;

    private List<String> itemsIds;

    private BigDecimal orderCost;
}