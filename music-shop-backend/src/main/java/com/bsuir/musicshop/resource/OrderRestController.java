package com.bsuir.musicshop.resource;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.bsuir.musicshop.model.*;
import com.bsuir.musicshop.service.OrderService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> findAllOrderDTOs() {
        return orderService.findAllOrderDTOs();
    }


    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Order findOrderById(@PathVariable Integer id) {
        return orderService.findOrderById(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addOrder(@RequestBody Order order) {
        orderService.addOrder(order);
    }

    @PutMapping("/{orderId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateOrder(@PathVariable Integer orderId, @RequestBody Order order) {
        orderService.updateOrder(order);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
    }

    @GetMapping(value = "/{from}/{to}")
    public List<OrderDto> findOrdersByDates(@PathVariable("from") String dateFrom, @PathVariable("to") String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom);
        LocalDate to = LocalDate.parse(dateTo);

        return orderService.findOrdersByDates(from, to);
    }

    @GetMapping(value = "/by-cost")
    public List<OrderDto> sortedOrders() {
        return orderService.findSortedDtos();
    }

    @GetMapping(value = "/stats")
    public Stats getStats() {
        return orderService.getStats();
    }

    @PutMapping(value = "/paid/{id}")
    public void markAsPaid(@PathVariable Integer id) {
        orderService.markIsPaid(id);
    }
}