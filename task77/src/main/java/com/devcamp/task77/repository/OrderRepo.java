package com.devcamp.task77.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.task77.model.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {

}
