package com.devcamp.pizza365.repository;

import com.devcamp.pizza365.model.CVoucher;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IVoucherRepository extends JpaRepository<CVoucher, Long> {
	@Query(value = "SELECT * FROM p_vouchers ORDER BY phan_tram_giam_gia DESC", nativeQuery = true)
	List<CVoucher> getCVoucherDESC();

	@Transactional
	@Modifying
	@Query(value = "UPDATE p_vouchers SET phan_tram_giam_gia = :phanTram WHERE ma_voucher  = :ma_voucher", nativeQuery = true)
	int updatePhanTram(@Param("ma_voucher") String maVoucher, @Param("phanTram") String phanTram);

}
