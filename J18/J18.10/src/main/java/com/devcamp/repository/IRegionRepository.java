package com.devcamp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcamp.model.CRegion;

@Repository
public interface IRegionRepository extends JpaRepository<CRegion, Long> {
	List<CRegion> findByCountryId(Long countryId);
	CRegion findByRegionCode(String regionCode);
	List<CRegion> findByCountryCountryCode(String countryCode);
	List<CRegion> findByCountryCountryName(String countryName);
	Optional<CRegion> findByIdAndCountryId(Long id, Long instructorId);
}
