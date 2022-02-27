package com.devcamp.pizza365.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.pizza365.model.COrder;

public interface IOrderRepository extends JpaRepository<COrder, Long>{

}
