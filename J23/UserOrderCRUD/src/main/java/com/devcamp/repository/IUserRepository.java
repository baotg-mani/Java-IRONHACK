package com.devcamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.model.CUser;

public interface IUserRepository extends JpaRepository<CUser, Long> {

}
