package com.devcamp.shop_pizza365.repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devcamp.shop_pizza365.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// JPA Query Native SQL
	@Query(value = "SELECT * FROM employees  WHERE first_name LIKE :firstname OR lastname LIKE :lastname ORDER BY employees.id DESC", nativeQuery = true)
	List<Employee> findEmployeeByFNameOrLName(@Param("firstname") String fName, @Param("lastname") String lName);

	@Query(value = "SELECT * FROM employees  WHERE office_code LIKE :officeCode", nativeQuery = true)
	List<Employee> findEmployeeByOfficeCode(@Param("officeCode") Integer param, Pageable pageable);

	@Query(value = "SELECT * FROM employees  WHERE job_title LIKE :jobTitle", nativeQuery = true)
	List<Employee> findEmployeeByByJobTittle(@Param("jobTitle") String param, Pageable pageable);

	@Query(value = "UPDATE employees SET job_title = 'jobTitleParam' WHERE job_title IS NULL;", nativeQuery = true)
	int updateJobTitleForNull(@Param("jobTitleParam") String param);

}
