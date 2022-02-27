package com.devcamp.pizza365.repository;

import com.devcamp.pizza365.model.CCountry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CCountry, Long> {
	CCountry findByCountryCode(String countryCode);
	
	//JPA Query JPQL
	@Query(value = "FROM #{#entityName} WHERE country_name LIKE ?1")
	List<CCountry> findCountryByCountryNameLike(String countryname);
	
	//JPA Query Native SQL
	@Query(value = "SELECT * FROM p_country  WHERE country_name LIKE :para1 ORDER BY country_name DESC", nativeQuery = true)
	List<CCountry> findCountryByCountryNameDesc(@Param("para1") String name1);
	
	@Query(value = "SELECT * FROM p_country  WHERE country_name LIKE %:paraName% ORDER BY country_name", nativeQuery = true)
	List<CCountry> findCountryByCountryName1(@Param("paraName") String paraName);
	
	@Query(value = "SELECT * FROM p_country  WHERE country_name LIKE :paraName% ORDER BY country_name DESC", nativeQuery = true)
	List<CCountry> findCountryByCountryName2(@Param("paraName") String paraName);
	
	@Query(value = "SELECT * FROM p_country  WHERE country_name LIKE %:paraName ORDER BY country_name DESC", nativeQuery = true)
	List<CCountry> findCountryByCountryName3(@Param("paraName") String paraName);
}
