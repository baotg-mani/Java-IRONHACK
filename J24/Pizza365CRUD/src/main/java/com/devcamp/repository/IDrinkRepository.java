package com.devcamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.model.CDrink;

public interface IDrinkRepository extends JpaRepository<CDrink, Long> {

}
