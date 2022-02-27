package com.devcamp.pizza365.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcamp.pizza365.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	 List<Order> findByCustomerId(Long customerId);
	 Optional<Order> findByIdAndCustomerId(Long id, Long customerId);
	 Order findByOrderDate(Date orderDate);
//	 Optional<CRegion> findByRegionCodeAndCountryCountryCode(String countryCode,String regionCode);
}
