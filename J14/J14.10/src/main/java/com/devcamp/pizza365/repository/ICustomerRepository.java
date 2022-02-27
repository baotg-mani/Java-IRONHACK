package com.devcamp.pizza365.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.pizza365.model.CCustomer;

public interface ICustomerRepository extends JpaRepository<CCustomer, Long> {

}
