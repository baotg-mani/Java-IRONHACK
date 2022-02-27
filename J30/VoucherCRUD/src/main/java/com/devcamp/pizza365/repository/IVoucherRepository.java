package com.devcamp.pizza365.repository;

import com.devcamp.pizza365.model.CCOrder;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IVoucherRepository extends JpaRepository<CCOrder, Long> {
	@Query(value = "SELECT * FROM vouchers ORDER BY phan_tram_giam_gia DESC", nativeQuery = true)
	List<CCOrder> getCVoucherDESC();

	@Query(value = "SELECT * FROM vouchers WHERE ngay_tao  <= :ngayTao ORDER BY phan_tram_giam_gia DESC", nativeQuery = true)
	List<CCOrder> getCVoucherDESCDate(@Param("ngayTao") Date ngayTao);

	@Transactional
	@Modifying
	@Query(value = "UPDATE vouchers SET phan_tram_giam_gia = :phanTram WHERE ma_voucher  = :ma_voucher", nativeQuery = true)
	int updatePhanTram(@Param("ma_voucher") String maVoucher, @Param("phanTram") String phanTram);

	@Transactional
	@Modifying
	@Query(value = "UPDATE vouchers SET phan_tram_giam_gia = :phanTram WHERE ngayTao  <= :ma_voucher", nativeQuery = true)
	int updatePhanTramDate(@Param("ma_voucher") Date maVoucher, @Param("phanTram") String phanTram);
}
