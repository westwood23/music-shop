package com.bsuir.musicshop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsuir.musicshop.model.Item;
import com.bsuir.musicshop.repository.ItemRepository;

import lombok.AllArgsConstructor;


@Service
@Transactional
@AllArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    public void addItem(Item item) {
        repository.save(item);
    }

    public void updateItem(Item item) {
        repository.save(item);
    }

    public void deleteItem(Integer itemId) {
        repository.deleteById(itemId);
    }

    public List<Item> findAllItems() {
        return repository.findAll();
    }

    public Item findItemById(Integer itemId) {
        return repository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }
}