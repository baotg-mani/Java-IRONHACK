package com.devcamp.task77.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devcamp.task77.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	@Query(value = "select * "
			+ "from customers c "
			+ "where c.first_name like %:firstName% or "
			+ "c.last_name like %:lastName%", nativeQuery = true)
	List<Customer> findCustomerByFirstNameAndLastName(@Param("firstName") String firstName,
			@Param("lastName") String lastName);
	
	@Query(value = "select * "
			+ "from customers c "
			+ "where c.city like %:city% or "
			+ "c.state like %:state%", nativeQuery = true)
	List<Customer> findCustomerByCityAndSate(@Param("city") String city,
			@Param("state") String state, Pageable pageable);
}
