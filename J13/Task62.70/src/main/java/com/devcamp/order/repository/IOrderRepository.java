package com.devcamp.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.order.model.COrder;

public interface IOrderRepository extends JpaRepository<COrder, Long> {
	
}
