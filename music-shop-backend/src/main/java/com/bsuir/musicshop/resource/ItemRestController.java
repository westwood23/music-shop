package com.bsuir.musicshop.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.bsuir.musicshop.model.Item;
import com.bsuir.musicshop.service.ItemService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemRestController {

    private final ItemService itemService;

    /**
     * Find all items
     *
     * @return Items list
     */
    @GetMapping
    public List<Item> findAllItems() {

        return itemService.findAllItems();
    }

    @GetMapping(value = "/{itemId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Item findItemById(@PathVariable Integer itemId) {
        return itemService.findItemById(itemId);
    }

    @PostMapping
    public void addItem(@RequestBody Item item) {
        itemService.addItem(item);
    }

    @PutMapping("/{itemId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateItem(@RequestBody Item item) {
        itemService.updateItem(item);
    }

    @DeleteMapping(value = "/{itemId}")
    public void deleteItem(@PathVariable Integer itemId) {
        itemService.deleteItem(itemId);
    }
}