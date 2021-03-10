package com.bsuir.musicshop.model;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item{

    private Integer itemId;

    private String itemName;

    private BigDecimal itemPrice;
}