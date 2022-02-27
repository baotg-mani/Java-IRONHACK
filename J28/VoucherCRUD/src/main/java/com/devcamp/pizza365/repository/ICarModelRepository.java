package com.devcamp.pizza365.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.pizza365.model.CarModel;

public interface ICarModelRepository extends JpaRepository<CarModel, Long> {
	CarModel findByCarCode(String carCode);
}
