package com.devcamp.shop_pizza365.repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devcamp.shop_pizza365.model.Office;

public interface OfficeRepository extends JpaRepository<Office, Integer> {

	// JPA Query Native SQL
	@Query(value = "SELECT * FROM offices  WHERE city LIKE :cityParam ORDER BY offices.id DESC", nativeQuery = true)
	List<Office> findOfficeByCity(@Param("cityParam") String param);

	@Query(value = "SELECT * FROM offices  WHERE state LIKE :stateParam", nativeQuery = true)
	List<Office> findOfficeByState(@Param("stateParam") String param, Pageable pageable);

	@Query(value = "SELECT * FROM offices  WHERE country LIKE :countryParam", nativeQuery = true)
	List<Office> findOfficeByByCountry(@Param("countryParam") String param, Pageable pageable);

	@Transactional
	@Modifying
	@Query(value = "UPDATE offices SET country = 'countryParam' WHERE country IS NULL;", nativeQuery = true)
	int updateCountryForNull(@Param("countryParam") String countryParam);

	@Query(value = "SELECT * FROM offices ORDER BY offices.id DESC", nativeQuery = true)
	List<Office> findAllOffice();
}
