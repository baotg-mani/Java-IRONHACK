package com.devcamp.pizza365.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.pizza365.model.COrder;

public interface IOrderRepository extends JpaRepository<COrder, Long> {
	List<COrder> findByUserId(Long userId);
}
