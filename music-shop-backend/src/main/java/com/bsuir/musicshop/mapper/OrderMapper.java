package com.bsuir.musicshop.mapper;


import com.bsuir.musicshop.model.Order;
import com.bsuir.musicshop.model.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderDto mapToDto(final Order source) {
        return OrderDto.builder()
                .orderCost(source.getOrderCost())
                .orderId(source.getOrderId())
                .orderDate(source.getOrderDate())
                .build();
    }
}