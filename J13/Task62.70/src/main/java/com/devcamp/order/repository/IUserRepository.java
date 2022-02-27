package com.devcamp.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.order.model.CCustomer;

public interface IUserRepository extends JpaRepository<CCustomer, Long> {

}
