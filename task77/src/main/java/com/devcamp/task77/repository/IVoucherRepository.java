package com.devcamp.task77.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.devcamp.task77.model.CVoucher;

@Repository
public interface IVoucherRepository extends JpaRepository<CVoucher, Long> {
	Optional<CVoucher> findByMaVoucher(String voucherCode);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE vouchers SET phan_tram_giam_gia = :phanTram WHERE ma_voucher  = :ma_voucher", nativeQuery = true)
	int updatePhanTram(@Param("ma_voucher") String maVoucher, @Param("phanTram") String phanTram);
}
