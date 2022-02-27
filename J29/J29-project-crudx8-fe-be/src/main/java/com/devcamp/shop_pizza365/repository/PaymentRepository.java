package com.devcamp.shop_pizza365.repository;

import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devcamp.shop_pizza365.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	// JPA Query Native SQL
		@Query(value = "SELECT * FROM payments  WHERE customer_id LIKE :paramCustomerId", nativeQuery = true)
		List<Payment> findPaymentByCustomerId(@Param("paramCustomerId") Integer cusId, Pageable pageable);
		
		@Query(value = "SELECT * FROM payments WHERE amount LIKE :paramAmount ORDER BY payments.id DESC", nativeQuery = true)
		List<Payment> findPaymentByAmount(@Param("paramAmount") BigDecimal param, Pageable pageable);
	
		@Transactional
		@Modifying
		@Query(value = "UPDATE payments SET check_number = 'param' WHERE check_number IS NULL;", nativeQuery = true)
		int updateCheckNumForNull(@Param("param") String param);

		@Query(value = "SELECT * FROM payments ORDER BY payments.id DESC", nativeQuery = true)
		List<Payment> findAllPayment();
		
		@Query(value = "SELECT * FROM payments WHERE payments.customer_id LIKE :paramCustomerId ORDER BY payments.id DESC", nativeQuery = true)
		List<Payment> findPaymentsByCustomerId(@Param("paramCustomerId") Integer cusId, Pageable pageable);
}
