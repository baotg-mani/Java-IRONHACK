package com.devcamp.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.car.model.CCar;

public interface ICarRepository extends JpaRepository<CCar, Long> {
	CCar findByCarCode(String carCodeee);
}
