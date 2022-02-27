package com.devcamp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.api.model.CRegion;

public interface IRegionRepository extends JpaRepository<CRegion, Long> {

}
