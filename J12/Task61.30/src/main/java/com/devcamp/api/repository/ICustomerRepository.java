package com.devcamp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.api.model.CCustomer;

public interface ICustomerRepository extends JpaRepository<CCustomer, Long>{

}
