package com.bsuir.musicshop.service;

import com.bsuir.musicshop.mapper.OrderMapper;
import com.bsuir.musicshop.model.Order;
import com.bsuir.musicshop.model.OrderDto;
import com.bsuir.musicshop.repository.ItemRepository;
import com.bsuir.musicshop.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class OrderServiceImplMockTest {

    @Mock
    private OrderRepository orderDao;
    @Mock
    private ItemRepository itemDao;
    @Autowired
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp(){
        initMocks(this);
        orderService = new OrderService(orderDao, itemDao, orderMapper);
    }

    private final static LocalDate FROM = LocalDate.of(2019, 10, 18);
    private final static LocalDate TO = LocalDate.of(2019, 10, 18);

    @Test
    void findAllDTOs(){
        Mockito.when(orderDao.findAll()).thenReturn(Collections.singletonList(create()));

        List<OrderDto> orders = orderService.findAllOrderDTOs();

        assertNotNull(orders);
        assertEquals(1, orders.size());

        Mockito.verify(orderDao, Mockito.times(1)).findAll();
    }

    @Test
    void findOrderById(){
        int id = 1;
        Mockito.when(orderDao.findById(id)).thenReturn(Optional.of(create()));

        Order order = orderService.findOrderById(id);

        assertTrue(order.getOrderId().equals(1));

        Mockito.verify(orderDao, Mockito.times(1)).findById(id);
    }

    @Test
    void findOrdersByDates(){
        Mockito.when(orderDao.findAllByOrderDateBetween(FROM, TO))
                .thenReturn(Collections.singletonList(create()));

        List<OrderDto> orders = orderService.findOrdersByDates(FROM, TO);
        assertEquals(1, orders.size());

        Mockito.verify(orderDao, Mockito.times(1)).findAllByOrderDateBetween(FROM, TO);
    }

    @Test
    void addOrder(){
        Order order = create();
        Mockito.doNothing().when(orderDao).save(order);

        order.setItemsIds(Arrays.asList());

        orderService.addOrder(order);

        Mockito.verify(orderDao, Mockito.times(1)).save(order);

    }

    @Test
    void deleteOrder(){
        int id = 1;
        Mockito.doNothing().when(orderDao).deleteById(id);

        orderService.deleteOrder(id);

        Mockito.verify(orderDao).deleteById(id);

    }

    private static Order create(){
        Order order = new Order();
        order.setOrderId(1);
        return order;
    }

    private static OrderDto createDto(){
        OrderDto dto = new OrderDto();
        dto.setOrderId(1);
        return dto;
    }
}