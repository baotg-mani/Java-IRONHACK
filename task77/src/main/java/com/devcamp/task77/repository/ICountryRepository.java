package com.devcamp.task77.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devcamp.task77.model.*;

@Repository
public interface ICountryRepository extends JpaRepository<CCountry, Long>{
	@Query(value = "select * "
			+ "from  countries c "
			+ "where c.country_name like %:countryName%  or "
			+ "c.country_code like %:countryName2 or "
			+ "c.country_name like :countryName3%", nativeQuery = true)
	List<CCountry> findCountryByCountryNameDesc(@Param("countryName") String countryName,
			@Param("countryName2") String countryName2,
			@Param("countryName3") String countryName3);
}
