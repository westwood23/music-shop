package com.bsuir.musicshop.consumer;

import com.bsuir.musicshop.model.Order;
import com.bsuir.musicshop.model.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Component
public class OrderRestConsumer {

    @Value("${rest.url}" + "${rest.orders}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public List<OrderDto> findAllOrderDTOs() {
        return restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OrderDto>>(){})
                .getBody();
    }

    public Order findOrderById(Integer orderId) {
        return restTemplate.getForEntity(url +"/"+orderId, Order.class).getBody();
    }

    public void addOrder(Order order) {
        restTemplate.postForEntity(url, order, Order.class);
    }

    public void updateOrder(Order order) {
        restTemplate.put(url+"/"+order.getOrderId(), order);
    }

    public void deleteOrder(Integer orderId) {
        restTemplate.delete(url+"/"+orderId);
    }

    public List<OrderDto> findOrdersByDates(LocalDate from, LocalDate to) {
        return restTemplate.exchange(url +"/"+from+"/"+to,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OrderDto>>(){})
                .getBody();
    }
}