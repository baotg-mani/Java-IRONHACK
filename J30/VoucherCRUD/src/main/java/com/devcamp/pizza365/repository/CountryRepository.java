package com.devcamp.pizza365.repository;

import com.devcamp.pizza365.model.CCountry;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface CountryRepository extends JpaRepository<CCountry, Long> {
	CCountry findByCountryCode(String countryCode);
	
	/**
	 * ĐÂy là method ấy danh sách country theo tên có phân trang.
	 * @param countryname
	 * @param pageable
	 * @return
	 */
	@Query(value = "FROM #{#entityName} WHERE country_name like ?1")
	List<CCountry> findCountryByCountryNameLike(String countryname, Pageable pageable);
	
	@Query(value = "SELECT * FROM country  WHERE country_name like :name ORDER BY country_name DESC", nativeQuery = true)
	List<CCountry> findCountryByCountryNameDesc(@Param("name") String name);
}
