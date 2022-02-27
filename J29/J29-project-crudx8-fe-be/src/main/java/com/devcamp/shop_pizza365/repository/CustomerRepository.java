package com.devcamp.shop_pizza365.repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devcamp.shop_pizza365.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	// JPA Query Native SQL
	@Query(value = "SELECT * FROM customers  WHERE first_name LIKE :firstname OR lastname LIKE :lastname ORDER BY customers.id DESC", nativeQuery = true)
	List<Customer> findCustomerByFNameOrLName(@Param("firstname") String fName, @Param("lastname") String lName);

	@Query(value = "SELECT * FROM customers  WHERE city LIKE :city ORDER BY customers.id DESC", nativeQuery = true)
	List<Customer> findCustomerByCity(@Param("city") String cityName, Pageable pageable);

	@Query(value = "SELECT * FROM customers  WHERE state LIKE :state ORDER BY customers.id DESC", nativeQuery = true)
	List<Customer> findCustomerByState(@Param("state") String stateName, Pageable pageable);

	@Query(value = "SELECT * FROM customers WHERE country LIKE :countryParam ORDER BY country DESC", nativeQuery = true)
	List<Customer> findCustomerByCountry(@Param("countryParam") String param, Pageable pageable);

	@Transactional
	@Modifying
	@Query(value = "UPDATE customers SET country = 'countryParam' WHERE country IS NULL;", nativeQuery = true)
	int updateCountryForNull(@Param("countryParam") String countryParam);

	@Query(value = "SELECT * FROM customers  ORDER BY customers.id DESC", nativeQuery = true)
	List<Customer> findAllCustomer();

	// query ra bảng số customer của 4 country USA, France, Singapore, Spain để làm
	// bar chart
	@Query(value = "SELECT country, COUNT(country) AS customers_number FROM customers WHERE country = 'usa' OR country = 'france' OR country = 'singapore' OR country = 'spain' GROUP BY country", nativeQuery = true)
	List<Object> numberCusOf4Countries();
}
