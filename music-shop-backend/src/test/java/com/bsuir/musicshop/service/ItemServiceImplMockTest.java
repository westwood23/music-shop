package com.bsuir.musicshop.service;

import com.bsuir.musicshop.model.Item;
import com.bsuir.musicshop.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.MockitoAnnotations.initMocks;

class ItemServiceImplMockTest {

    @Mock
    private ItemRepository itemDao;

    @InjectMocks
    private ItemService itemService;

    @Captor
    ArgumentCaptor<Item> captor;

    @BeforeEach
    void setUp() {
        initMocks(this);
        itemService = new ItemService(itemDao);
    }

    @Test
    void findAllItems(){
        Mockito.when(itemDao.findAll()).thenReturn(Collections.singletonList(createItem()));

        List<Item> items = itemService.findAllItems();

        assertNotNull(items);
        assertEquals(1, items.size());

        Mockito.verify(itemDao).findAll();
    }


    @Test
    void findById(){
        int id = 1;

        Mockito.when(itemDao.findById(id)).thenReturn(Optional.of(createItem()));

        Item item = itemService.findItemById(id);

        assertEquals("Item", item.getItemName());

        Mockito.verify(itemDao).findById(id);

    }

    @Test
    void addItem(){
        Item item = createItem();

        Mockito.when(itemDao.save(item)).thenReturn(item);

        itemService.addItem(item);

        Mockito.verify(itemDao).save(item);
    }

    @Test
    void updateItem(){
        itemService.updateItem(createItem());

        Mockito.verify(itemDao).save(captor.capture());

        Item item = captor.getValue();
        assertNotNull(item);
        assertEquals("Item", item.getItemName());
    }

    @Test
    void deleteItem(){
        int id = 1;
        Mockito.doNothing().when(itemDao).deleteById(id);

        itemService.deleteItem(id);

        Mockito.verify(itemDao).deleteById(id);
    }


    private static Item createItem() {
        Item item = new Item();
        item.setItemName("Item");
        return item;
    }

}