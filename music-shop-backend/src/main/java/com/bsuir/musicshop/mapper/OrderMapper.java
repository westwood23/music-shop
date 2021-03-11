package com.bsuir.musicshop.mapper;


import org.springframework.stereotype.Component;

import com.bsuir.musicshop.model.Order;
import com.bsuir.musicshop.model.OrderDto;

@Component
public class OrderMapper {
    public OrderDto mapToDto(final Order source) {
        return OrderDto.builder()
                .orderCost(source.getOrderCost())
                .orderId(source.getOrderId())
                .orderDate(source.getOrderDate())
                .isPaid(source.getIsPaid())
                .build();
    }
}