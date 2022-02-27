package com.devcamp.shop_pizza365.repository;

import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devcamp.shop_pizza365.model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{

	// JPA Query Native SQL
		@Query(value = "SELECT * FROM order_details  WHERE order_id LIKE :orderId OR product_id LIKE :productId", nativeQuery = true)
		List<OrderDetail> findOrderDetailByOrderIdOrProductId(@Param("orderId") Integer param1, @Param("productId") Integer param2);
		
		@Query(value = "SELECT * FROM order_details WHERE quantity_order LIKE :paramQuantity ORDER BY order_details.id DESC", nativeQuery = true)
		List<OrderDetail> findOrderDetailByQuantityOrder(@Param("paramQuantity") Integer paramQuantity, Pageable pageable);
		
		@Query(value = "UPDATE order_details SET price_each = 'paramPrice' WHERE price_each IS NULL;", nativeQuery = true)
		int updatePriceForNull(@Param("paramPrice") BigDecimal paramPrice);

}
