package com.bsuir.musicshop.service;

import com.bsuir.musicshop.MusicShopApplication;
import com.bsuir.musicshop.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MusicShopApplication.class)
//@Sql({"classpath:/schema.sql", "classpath:/data.sql"})
@Transactional
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    void findAllItems() {
        List<Item> items = itemService.findAllItems();
        assertNotNull(items);
        assertFalse(items.isEmpty());
    }

    @Test
    void findItemById() {
        int id = 1;
        Item item = itemService.findItemById(id);

        assertNotNull(item);
        assertEquals("Gibson Les Paul", item.getItemName());
    }

    @Test
    void addItem() {
        int sizeBefore = itemService.findAllItems().size();

        Item item = new Item();
        item.setItemName("Guitar");
        item.setItemPrice(new BigDecimal("1000"));

        itemService.addItem(item);

        assertEquals((sizeBefore + 1), itemService.findAllItems().size());
    }

    @Test
    void updateItem() {
        Item item = itemService.findItemById(1);
        item.setItemName("Guitar");

        itemService.updateItem(item);

        Item item1 = itemService.findItemById(item.getItemId());
        assertNotNull(item1);
        assertEquals("Guitar", item1.getItemName());
    }

    @Test
    void deleteItem() {
        int sizeBefore = itemService.findAllItems().size();

        itemService.deleteItem(16);
        assertEquals(sizeBefore - 1, itemService.findAllItems().size());
    }
}