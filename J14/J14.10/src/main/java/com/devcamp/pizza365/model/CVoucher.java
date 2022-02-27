package com.devcamp.pizza365.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *	Tạo bảng vouchers tương ứng
 */
@Entity
@Table(name = "vouchers")
public class CVoucher {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "ma_voucher")		
	private String maVoucher;
	
	@Column(name = "phan_tram_giam_gia")
	private String phanTramGiamGia;
	
	@Column(name = "ghi_chu")
	private String ghiChu;	
	
	@Column(name = "ngay_tao")
	private long ngayTao;
	
	@Column(name = "ngay_cap_nhat")
	private long ngayCapNhat;
	
	
	public CVoucher() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CVoucher(long id, String maVoucher, String phanTramGiamGia, String ghiChu, long ngayTao, long ngayCapNhat) {
		super();
		this.id = id;
		this.maVoucher = maVoucher;
		this.phanTramGiamGia = phanTramGiamGia;
		this.ghiChu = ghiChu;
		this.ngayTao = ngayTao;
		this.ngayCapNhat = ngayCapNhat;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMaVoucher() {
		return maVoucher;
	}
	public void setMaVoucher(String maVoucher) {
		this.maVoucher = maVoucher;
	}
	public String getPhanTramGiamGia() {
		return phanTramGiamGia;
	}
	public void setPhanTramGiamGia(String phanTramGiamGia) {
		this.phanTramGiamGia = phanTramGiamGia;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public long getNgayTao() {
		return ngayTao;
	}
	public void setNgayTao(long ngayTao) {
		this.ngayTao = ngayTao;
	}
	public long getNgayCapNhat() {
		return ngayCapNhat;
	}
	public void setNgayCapNhat(long ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}
}
