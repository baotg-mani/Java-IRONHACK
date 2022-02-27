package com.devcamp.UserOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.UserOrder.model.CUser;

public interface IUserRepository extends JpaRepository<CUser, Long> {

}
