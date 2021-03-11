package com.bsuir.musicshop.model;

import java.math.BigDecimal;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item{

    private Integer itemId;

    private String itemName;

    private BigDecimal itemPrice;
}