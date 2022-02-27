package com.devcamp.HW.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.HW.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {

}
