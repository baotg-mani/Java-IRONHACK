package com.devcamp.country.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.country.model.CRegion;

public interface IRegionRepository extends JpaRepository<CRegion, Long> {

}
