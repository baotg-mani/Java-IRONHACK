package com.devcamp.pizza365.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.pizza365.model.CDrink;

public interface IDrinkRepository extends JpaRepository<CDrink, Long> {

}
