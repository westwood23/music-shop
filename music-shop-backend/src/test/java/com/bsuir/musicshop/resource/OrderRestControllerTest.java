package com.bsuir.musicshop.resource;

import com.bsuir.musicshop.model.Order;
import com.bsuir.musicshop.model.OrderDto;
import com.bsuir.musicshop.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderRestController.class)
class OrderRestControllerTest {

    @MockBean
    private OrderService service;

    @Autowired
    private MockMvc mock;

    private ObjectMapper mapper = new ObjectMapper();


    private final static LocalDate FROM = LocalDate.of(2019, 10, 18);
    private final static LocalDate TO = LocalDate.of(2019, 10, 18);

    @Test
    void findAllOrders() throws Exception{
        Mockito.when(service.findAllOrderDTOs()).thenReturn(Arrays.asList(createDto(1), createDto(2)));

        mock.perform(MockMvcRequestBuilders.get("/orders")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId", Matchers.is(1)))
                .andExpect(jsonPath("$[1].orderId", Matchers.is(2)));

        Mockito.verify(service, times(1)).findAllOrderDTOs();
    }

    @Test
    void findOrdersByDates() throws Exception{
        Mockito.when(service.findOrdersByDates(FROM, TO)).thenReturn(Arrays.asList(createDto(1), createDto(2)));

        mock.perform(MockMvcRequestBuilders.get("/orders/{FROM}/{TO}", FROM, TO)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId", Matchers.is(1)))
                .andExpect(jsonPath("$[1].orderId", Matchers.is(2)));

        Mockito.verify(service, times(1)).findOrdersByDates(FROM, TO);
    }

    @Test
    void findOrderById() throws Exception{
        int id = 1;
        Mockito.when(service.findOrderById(id)).thenReturn(createOrder(id));

        mock.perform(MockMvcRequestBuilders.get("/orders/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId", Matchers.is(1)));

        Mockito.verify(service, times(1)).findOrderById(id);
    }

    @Test
    void addOrder() throws Exception{
        int id = 1;
        Mockito.doNothing().when(service).addOrder(createOrder(id));

        mock.perform(MockMvcRequestBuilders.post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createOrder(id)))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        Mockito.verify(service, times(1)).addOrder(any(Order.class));
    }

    @Test
    void updateOrder() throws Exception{
        int id = 1;
        Mockito.doNothing().when(service).updateOrder(createOrder(id));

        mock.perform(MockMvcRequestBuilders.put("/orders/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createOrder(id)))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        Mockito.verify(service, Mockito.times(1)).updateOrder(any(Order.class));
    }

    @Test
    void deleteOrder() throws Exception{
        int id = 1;
        Mockito.doNothing().when(service).deleteOrder(id);

        mock.perform(MockMvcRequestBuilders.delete("/orders/{id}", id))
                .andExpect(status().isOk());

        Mockito.verify(service, Mockito.times(1)).deleteOrder(id);
    }

    private OrderDto createDto(Integer orderId) {
        OrderDto dto = new OrderDto();
        dto.setOrderId(orderId);
        return dto;
    }

    private Order createOrder(Integer orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setItemsIds(Arrays.asList());
        return order;
    }
}
