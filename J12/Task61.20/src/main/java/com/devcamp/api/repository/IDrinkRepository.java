package com.devcamp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.api.model.CDrink;

public interface IDrinkRepository extends JpaRepository<CDrink, Long> {
	
}
