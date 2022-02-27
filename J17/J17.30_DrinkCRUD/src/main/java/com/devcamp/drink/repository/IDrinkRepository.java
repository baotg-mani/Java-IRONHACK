package com.devcamp.drink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.drink.model.CDrink;

public interface IDrinkRepository extends JpaRepository<CDrink, Long> {

}
