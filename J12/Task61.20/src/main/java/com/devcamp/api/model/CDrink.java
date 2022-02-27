package com.devcamp.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "drinks")
public class CDrink {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "ma_nuoc_uong")
	private String maNuocUong;

	@Column(name = "ten_nuoc_uong")
	private String tenNuocUong;

	@Column(name = "don_gia")
	private int donGia;

	@Column(name = "ngay_tao")
	private String ngayTao;

	@Column(name = "ngay_cap_nhat")
	private String ngayCapNhat;

	public CDrink() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CDrink(long id, String maNuocUong, String tenNuocUong, int donGia, String ngayTao, String ngayCapNhat) {
		super();
		this.id = id;
		this.maNuocUong = maNuocUong;
		this.tenNuocUong = tenNuocUong;
		this.donGia = donGia;
		this.ngayTao = ngayTao;
		this.ngayCapNhat = ngayCapNhat;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMaNuocUong() {
		return maNuocUong;
	}

	public void setMaNuocUong(String maNuocUong) {
		this.maNuocUong = maNuocUong;
	}

	public String getTenNuocUong() {
		return tenNuocUong;
	}

	public void setTenNuocUong(String tenNuocUong) {
		this.tenNuocUong = tenNuocUong;
	}

	public long getDonGia() {
		return donGia;
	}

	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}

	public String getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}

	public String getNgayCapNhat() {
		return ngayCapNhat;
	}

	public void setNgayCapNhat(String ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}

}
