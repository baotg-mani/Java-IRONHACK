package com.devcamp.task77.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.task77.model.ProductLine;

public interface ProductLineRepo extends JpaRepository<ProductLine, Integer> {

}
