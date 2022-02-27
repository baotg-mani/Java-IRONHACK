package com.devcamp.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.car.model.CCarType;

public interface ICarTypeRepository extends JpaRepository<CCarType, Long> {

}
