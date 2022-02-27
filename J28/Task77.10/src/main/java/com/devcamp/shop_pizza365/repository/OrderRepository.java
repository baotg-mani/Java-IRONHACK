package com.devcamp.shop_pizza365.repository;

import org.springframework.data.domain.Pageable;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devcamp.shop_pizza365.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	// JPA Query Native SQL
		@Query(value = "SELECT * FROM orders  WHERE customer_id LIKE :cusParam", nativeQuery = true)
		List<Order> findOrderByCustomerId(@Param("cusParam") Integer cusParam);

		@Query(value = "SELECT * FROM orders  WHERE order_date LIKE :dateParam", nativeQuery = true)
		List<Order> findOrderByOrderDate(@Param("dateParam") Timestamp orderDateParam, Pageable pageable);
		
		@Query(value = "SELECT * FROM orders WHERE status LIKE :statusParam ORDER BY country DESC", nativeQuery = true)
		List<Order> findOrderByStatus(@Param("statusParam") String statusParam, Pageable pageable);
		
		@Query(value = "UPDATE orders SET comments = 'cmtParam' WHERE comments IS NULL;", nativeQuery = true)
		int updateCommentsForNull(@Param("cmtParam") String cmtParam);

}
