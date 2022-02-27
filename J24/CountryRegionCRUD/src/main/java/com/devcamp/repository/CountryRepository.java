package com.devcamp.repository;

import com.devcamp.model.CCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CCountry, Long> {
	CCountry findByCountryCode(String countryCode);
	CCountry findByCountryName(String countryName);
}
