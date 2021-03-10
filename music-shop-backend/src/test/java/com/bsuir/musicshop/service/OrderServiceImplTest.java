//package com.bsuir.musicshop.service;
//
//import com.bsuir.musicshop.MusicShopApplication;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(classes = MusicShopApplication.class)
////@Sql({"classpath:/schema.sql", "classpath:/data.sql"})
//@Transactional
//class OrderServiceImplTest {
//
//    @Autowired
//    private OrderService orderService;
//
//    @Autowired
//    private ItemService itemService;
//
//    @Autowired
//    @Qualifier("itemRepository")
//    private ItemDao itemDao;
//
//    @Autowired
//    @Qualifier("orderRepository")
//    private OrderDao orderDao;
//
//    private final static LocalDate FROM = LocalDate.now();
//    private final static LocalDate TO = LocalDate.now();
//
//    @Test
//    void findOrderById(){
//        Order order = orderService.findOrderById(1);
//
//        assertNotNull(order);
//        assertFalse(order.getItemsList().isEmpty());
//    }
//
//    @Test
//    void findAllDtos(){
//        List<OrderDTO> orders = orderService.findAllOrderDTOs();
//        assertFalse(orders.isEmpty());
//    }
//
//    @Test
//    void findDTOsByDates(){
//        Order order = new Order();
//        order.setItemsIds(Collections.singletonList("1"));
//        orderService.addOrder(order);
//
//        List<OrderDTO> orders = orderService.findOrdersByDates(FROM, TO);
//        assertEquals(1, orders.size());
//    }
//
////    @Test
////    void addOrder(){
////        Order order = new Order();
////
////        List<String> itemsIds = new ArrayList<>();
////        itemsIds.add("15");
////        itemsIds.add("16");
////        order.setItemsIds(itemsIds);
////
////        System.out.println(order);
////        orderService.addOrder(order);
////
////        Order order1 = orderService.findOrderById(4);
////        System.out.println(order1);
////        assertFalse(order1.getItemsList().isEmpty());
////    }
//
//
//    @Test
//    void updateOrder(){
//
//        Order order = orderService.findOrderById(1);
//
//        List<Item> itemsBefore = order.getItemsList();
//
//        Item item1 = new Item("qwe", new BigDecimal("123"));
//        Item item2 = new Item("qwe1", new BigDecimal("124"));
//
//        itemDao.addItem(item1);
//        itemDao.addItem(item2);
//
//        List<String> itemsIds = new ArrayList<>();
//        itemsIds.add(item1.getItemId().toString());
//        itemsIds.add(item2.getItemId().toString());
//        order.setItemsIds(itemsIds);
//
//        orderService.updateOrder(order);
//
//        Order order1 = orderService.findOrderById(1);
//
//        List<Item>itemsAfter = order1.getItemsList();
//
//        assertEquals(itemsBefore.get(0), itemsAfter.get(0));
//        assertEquals(itemsBefore.get(1), itemsAfter.get(1));
//    }
//
//    @Test
//    void deleteOrder(){
//        int id = 1;
//        int sizeBefore = orderService.findAllOrderDTOs().size();
//        orderService.deleteOrder(id);
//
//
//        assertEquals(sizeBefore-1, orderService.findAllOrderDTOs().size());
//    }
//}
