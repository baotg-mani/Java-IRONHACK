package com.devcamp.pizza365.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcamp.pizza365.model.COrder;

@Repository
public interface IOrderRepository extends JpaRepository<COrder, Long> {
	COrder findByOrderCode(String orderId);
}
