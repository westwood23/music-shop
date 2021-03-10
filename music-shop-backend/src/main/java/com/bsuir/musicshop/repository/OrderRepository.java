package com.bsuir.musicshop.repository;

import com.bsuir.musicshop.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer>{

    List<Order> findAll();

    List<Order> findAllByOrderDateBetween(LocalDate from, LocalDate to);
}