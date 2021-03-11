package com.bsuir.musicshop.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bsuir.musicshop.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer>{

    List<Order> findAll();

    List<Order> findAllByOrderDateBetween(LocalDate from, LocalDate to);
}