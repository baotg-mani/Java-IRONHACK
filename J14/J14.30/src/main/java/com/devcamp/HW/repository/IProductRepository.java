package com.devcamp.HW.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.HW.model.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {

}
