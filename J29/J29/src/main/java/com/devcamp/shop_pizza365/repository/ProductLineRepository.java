package com.devcamp.shop_pizza365.repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devcamp.shop_pizza365.model.ProductLine;

public interface ProductLineRepository extends JpaRepository<ProductLine, Integer> {

	// JPA Query Native SQL
	@Query(value = "SELECT * FROM product_lines  WHERE state LIKE :paramId ORDER BY product_lines.id DESC", nativeQuery = true)
	List<ProductLine> findProductLineById(@Param("paramId") Integer paramId, Pageable pageable);

	@Query(value = "SELECT * FROM product_lines WHERE product_line LIKE :paramProducLine ORDER BY product_lines.id DESC", nativeQuery = true)
	List<ProductLine> findProductLineByLine(@Param("paramProducLine") String paramProducLine, Pageable pageable);

	@Transactional
	@Modifying
	@Query(value = "UPDATE product_lines SET description = 'param' WHERE description IS NULL;", nativeQuery = true)
	int updateDescriptionForNull(@Param("param") String param);
	
	@Query(value = "SELECT * FROM product_lines ORDER BY product_lines.id DESC", nativeQuery = true)
	List<ProductLine> findAllProductLine();
}
