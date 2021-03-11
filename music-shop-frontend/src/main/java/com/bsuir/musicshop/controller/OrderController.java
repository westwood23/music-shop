package com.bsuir.musicshop.controller;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.bsuir.musicshop.consumer.ItemRestConsumer;
import com.bsuir.musicshop.consumer.OrderRestConsumer;
import com.bsuir.musicshop.model.*;
import com.bsuir.musicshop.validator.OrderValidator;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderRestConsumer orderRestConsumer;

    private final ItemRestConsumer itemRestConsumer;

    private final OrderValidator validator;

    @Autowired
    public OrderController(OrderRestConsumer orderRestConsumer, ItemRestConsumer itemRestConsumer, OrderValidator validator) {
        this.orderRestConsumer = orderRestConsumer;
        this.itemRestConsumer = itemRestConsumer;
        this.validator = validator;
    }

    @GetMapping
    public final String orders(Model model){
        model.addAttribute("orders", orderRestConsumer.findAllOrderDTOs());
        return "orders";
    }

    @GetMapping(value = "/by-cost")
    public final String sortedByCost(Model model) {
        model.addAttribute("orders", orderRestConsumer.findSortedOrdersDtos());
        return "orders";
    }

    @GetMapping(value = "/{id}")
    public final String deleteOrder(@PathVariable Integer id){
        this.orderRestConsumer.deleteOrder(id);
        return "redirect:/orders";
    }

    @GetMapping("/new")
    public final String goToAddOrderPage(Model model){

        Order order = new Order();
        List<Item> items = itemRestConsumer.findAllItems();
        model.addAttribute("isNew", true);
        model.addAttribute("order", order);
        model.addAttribute("items", items);
        return "order";
    }

    @PostMapping("/new")
    public final String addOrder(@Valid Order order, BindingResult result, Model model) {
        validator.validate(order, result);
        if (result.hasErrors()) {
            model.addAttribute("isNew", true);
            return "order";
        } else {
            this.orderRestConsumer.addOrder(order);
            return "redirect:/orders";
        }
    }

    @GetMapping(value = "/edit/{id}")
    public final String goToEditOrderPage(@PathVariable Integer id, Model model){
        Order order = orderRestConsumer.findOrderById(id);
        List<Item> items = Stream.of(itemRestConsumer.findAllItems(), order.getItemsList()).
                flatMap(Collection::stream)
                .collect(Collectors.toList());
        model.addAttribute("order", order);
        model.addAttribute("items", items);
        return "order";
    }

    @PostMapping(value = "/edit/{id}")
    public final String updateOrder(@Valid Order order, BindingResult result) {
        validator.validate(order, result);
        if (result.hasErrors()) {
            return "order";
        } else {
            this.orderRestConsumer.updateOrder(order);
            return "redirect:/orders";
        }
    }

    @GetMapping(value = "/orderview/{id}")
    public final String orderView(@PathVariable Integer id, Model model){
        Order order = orderRestConsumer.findOrderById(id);
        List<Item> items = order.getItemsList();

        model.addAttribute("order", order);
        model.addAttribute("items", items);
        return "orderview";
    }

    @GetMapping(value = "/{from}/{to}")
    public String filterByDates(@PathVariable("from") String dateFrom, @PathVariable("to") String dateTo, Model model) {
        LocalDate from = LocalDate.parse(dateFrom);
        LocalDate to = LocalDate.parse(dateTo);

        model.addAttribute("orders", orderRestConsumer.findOrdersByDates(from, to));
        return "orders";
    }

    @GetMapping(value = "/stats")
    public String getStats(Model model) {
        Stats stats = orderRestConsumer.getStats();
        model.addAttribute("stats", stats);
        return "statistics";
    }

    @PostMapping(value = "/paid/{id}")
    public String setAsPaid(@PathVariable Integer id) {
        orderRestConsumer.markAsPaid(id);
        return "redirect:/orders";
    }
}