package com.devcamp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.api.model.CProduct;

public interface IProductRepository extends JpaRepository<CProduct, Long>{

}
