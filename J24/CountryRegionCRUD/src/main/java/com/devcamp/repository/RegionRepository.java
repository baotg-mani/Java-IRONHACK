package com.devcamp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcamp.model.CRegion;

@Repository
public interface RegionRepository extends JpaRepository<CRegion, Long> {
	 List<CRegion> findByCountryId(Long countryId);
	 Optional<CRegion> findByIdAndCountryId(Long id, Long instructorId);
	 CRegion findByRegionCode(String regionCode);
	 CRegion findByRegionName(String regionName);
	 Long countByCountryId(Long countryId);
}

