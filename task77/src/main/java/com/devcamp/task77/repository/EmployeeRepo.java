package com.devcamp.task77.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcamp.task77.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
