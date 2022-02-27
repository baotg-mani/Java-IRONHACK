package com.devcamp.task77.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcamp.task77.model.OrderDetail;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, Integer> {

}
