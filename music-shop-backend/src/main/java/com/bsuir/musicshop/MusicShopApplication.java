package com.bsuir.musicshop;

import com.bsuir.musicshop.model.Item;
import com.bsuir.musicshop.model.Order;
import com.bsuir.musicshop.service.ItemService;
import com.bsuir.musicshop.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@SpringBootApplication
public class MusicShopApplication {

    public static void main(String[] args) {
       SpringApplication.run(MusicShopApplication.class, args);
//        OrderService service = context.getBean(OrderService.class);
//        ItemService itemService = context.getBean(ItemService.class);
//        Item item = new Item();
//        item.setItemPrice(new BigDecimal("123"));
//        item.setItemName("123");
//
//        itemService.addItem(item);
//
//        Order order = new Order();
//        order.setItemsIds(itemService.findAllItems().stream().map(Item::getItemId).map(String::valueOf).collect(Collectors.toList()));
//        service.addOrder(order);
//
//        service.createBill(service.findAllOrderDTOs().get(0).getOrderId());
    }
}