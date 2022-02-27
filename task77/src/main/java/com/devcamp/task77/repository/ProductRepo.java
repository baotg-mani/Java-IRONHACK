package com.devcamp.task77.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.task77.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
