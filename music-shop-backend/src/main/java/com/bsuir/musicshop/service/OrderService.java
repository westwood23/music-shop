package com.bsuir.musicshop.service;

import com.bsuir.musicshop.mapper.OrderMapper;
import com.bsuir.musicshop.model.*;
import com.bsuir.musicshop.repository.ItemRepository;
import com.bsuir.musicshop.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final OrderMapper mapper;

    public void addOrder(Order order) {

        order.setOrderDate(LocalDate.now());
        order.setOrderCost(calculateCost(order));

        orderRepository.save(order);
    }

    public void updateOrder(Order order) {

        List<Item> items = order.getItemsIds()
                .stream()
                .map(Integer::parseInt)
                .map(itemRepository::findById)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());

        order.setItemsList(items);
        order.setOrderCost(calculateCost(order));

        orderRepository.save(order);
    }

    public void deleteOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    public List<OrderDto> findAllOrderDTOs() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    public Order findOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<OrderDto> findOrdersByDates(LocalDate from, LocalDate to) {
        return orderRepository.findAllByOrderDateBetween(from, to)
                .stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    private BigDecimal calculateCost(Order order) {
        return order.getItemsIds()
                .stream()
                .map(Integer::parseInt)
                .map(itemRepository::findById)
                .flatMap(Optional::stream)
                .map(Item::getItemPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}