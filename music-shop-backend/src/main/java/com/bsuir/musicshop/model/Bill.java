package com.bsuir.musicshop.model;

import lombok.Data;

@Data
public class Bill {
    private String number;
    private String date;
    private String cost;
    private String itemsCount;
}