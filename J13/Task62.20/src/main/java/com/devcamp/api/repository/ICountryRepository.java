package com.devcamp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.api.model.CCountry;

public interface ICountryRepository extends JpaRepository<CCountry, Long> {
	CCountry findByCountryCode(String countryCode);
}
