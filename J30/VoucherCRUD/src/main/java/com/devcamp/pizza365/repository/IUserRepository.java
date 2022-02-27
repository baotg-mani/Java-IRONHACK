package com.devcamp.pizza365.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcamp.pizza365.model.CUser;

@Repository
public interface IUserRepository extends JpaRepository<CUser, Long>{
	CUser findByUsername(String username);
	//CUser findByEmail(String email);

}
