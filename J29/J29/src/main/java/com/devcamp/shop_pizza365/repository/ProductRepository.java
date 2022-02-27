package com.devcamp.shop_pizza365.repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devcamp.shop_pizza365.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	// JPA Query Native SQL
	@Query(value = "SELECT * FROM products  WHERE city LIKE :paramProName ORDER BY product_name DESC", nativeQuery = true)
	List<Product> findProductByProName(@Param("paramProName") String paramProName, Pageable pageable);

	@Query(value = "SELECT * FROM products  WHERE product_code LIKE :paramProCode", nativeQuery = true)
	List<Product> findProductByProCode(@Param("paramProCode") String paramProCode, Pageable pageable);

	@Query(value = "SELECT * FROM products WHERE product_line_id LIKE :paramProLineId ORDER BY product_name DESC", nativeQuery = true)
	List<Product> findProductByProLineId(@Param("paramProLineId") Integer paramProLineId, Pageable pageable);

	@Transactional
	@Modifying
	@Query(value = "UPDATE products SET product_vendor = 'paramProductVender' WHERE product_vendor IS NULL;", nativeQuery = true)
	int updateVendorForNull(@Param("paramProductVender") String paramProductVender);

	@Query(value = "SELECT * FROM products ORDER BY id DESC", nativeQuery = true)
	List<Product> findAllProduct();
	
	@Query(value = "SELECT * FROM products AS p WHERE p.product_line_id LIKE :paramProductLineId ORDER BY p.id DESC", nativeQuery = true)
	List<Product> findProductsByProductLineId(@Param (value = "paramProductLineId") Integer productLineId, Pageable pageable);
}
