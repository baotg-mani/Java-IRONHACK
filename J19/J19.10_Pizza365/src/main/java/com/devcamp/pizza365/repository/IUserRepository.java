package com.devcamp.pizza365.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.pizza365.model.CUser;

public interface IUserRepository extends JpaRepository<CUser, Long> {

}
