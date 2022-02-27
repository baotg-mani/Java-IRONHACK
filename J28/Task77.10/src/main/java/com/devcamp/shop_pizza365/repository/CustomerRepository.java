package com.devcamp.shop_pizza365.repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devcamp.shop_pizza365.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	// JPA Query Native SQL
	@Query(value = "SELECT * FROM customers  WHERE first_name LIKE :firstname OR lastname LIKE :lastname ORDER BY customers.id DESC", nativeQuery = true)
	List<Customer> timCustomerByFNameOrLName(@Param("firstname") String fName, @Param("lastname") String lName);

	@Query(value = "SELECT * FROM customers  WHERE city LIKE :city ORDER BY customers.id DESC", nativeQuery = true)
	List<Customer> timCustomerByCity(@Param("city") String cityName, Pageable pageable);

	@Query(value = "SELECT * FROM customers  WHERE state LIKE :state ORDER BY customers.id DESC", nativeQuery = true)
	List<Customer> timCustomerByState(@Param("state") String stateName, Pageable pageable);

	@Query(value = "SELECT * FROM customers WHERE country LIKE :countryParam ORDER BY country DESC", nativeQuery = true)
	List<Customer> timCustomerByCountry(@Param("countryParam") String param, Pageable pageable);

	@Query(value = "UPDATE customers SET country = 'countryParam' WHERE country IS NULL;", nativeQuery = true)
	int updateCountryForNull(@Param("countryParam") String countryParam);

}
