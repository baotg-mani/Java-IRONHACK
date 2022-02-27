package com.devcamp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.model.COrder;

public interface IOrderRepository extends JpaRepository<COrder, Long> {
	List<COrder> findByUserId(Long userId);
}
