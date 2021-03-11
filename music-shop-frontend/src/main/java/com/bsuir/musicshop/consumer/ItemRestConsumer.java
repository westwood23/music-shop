package com.bsuir.musicshop.consumer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bsuir.musicshop.model.Item;

@Component
public class ItemRestConsumer {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${rest.url}" + "${rest.items}")
    private String url;

    public Item findItemById(Integer itemId) {

        return restTemplate.getForEntity(url+"/"+itemId, Item.class).getBody();
    }

    public List<Item> findAllItems() {

        System.out.println("test");

        return restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Item>>(){}).getBody();
    }

    public void addItem(Item item) {
        restTemplate.postForEntity(url, item, Item.class).getBody();
    }

    public void updateItem(Item item) {
        restTemplate.put(url + "/"+ item.getItemId(), item);
    }

    public void deleteItem(Integer itemId) {
        restTemplate.delete(url+"/"+itemId);
    }
}