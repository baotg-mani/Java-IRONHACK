package com.devcamp.HW.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.HW.model.Order;

public interface IOrderRepository extends JpaRepository<Order, Long> {

}
