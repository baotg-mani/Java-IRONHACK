package com.devcamp.country.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.country.model.CCountry;

public interface ICountryRepository extends JpaRepository<CCountry, Long> {
	CCountry findByCountryCode(String countryCode);
}
