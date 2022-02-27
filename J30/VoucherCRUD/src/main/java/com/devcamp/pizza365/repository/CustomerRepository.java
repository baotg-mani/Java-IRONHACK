package com.devcamp.pizza365.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcamp.pizza365.entity.Customer;
import com.devcamp.pizza365.entity.Order;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
