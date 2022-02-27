package com.devcamp.pizza365.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.pizza365.model.CarType;

public interface ICarTypeRepository extends JpaRepository<CarType, Long> {

}
