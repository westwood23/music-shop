package com.bsuir.musicshop.repository;

import com.bsuir.musicshop.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {

    List<Item> findAll();

    @Query(name = "getNotReservedItems")
    List<Item> findAllFree();
}