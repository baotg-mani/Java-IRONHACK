package com.devcamp.repository;

import com.devcamp.model.CCountry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountryRepository extends JpaRepository<CCountry, Long> {
	CCountry findByCountryCode(String countryCode);
	List<CCountry> findByCountryName(String countryName);
	CCountry findByRegionsRegionCode(String regionCode);
	List<CCountry> findByRegionsRegionName(String regionName);
}
