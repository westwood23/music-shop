package com.bsuir.musicshop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bsuir.musicshop.model.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {

    List<Item> findAll();

}