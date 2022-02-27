package com.devcamp.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class CProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "ten_san_pam")
	private String tenSanPham;

	@Column(name = "ma_san_pham")
	private String maSanPham;
	
	@Column(name = "gia_tien")
	private int giaTien;

	@Column(name = "ngay_tao")
	private String ngayTao;

	@Column(name = "ngay_cap_nhat")
	private String ngayCapNhat;

	public CProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CProduct(long id, String tenSanPham, String maSanPham, int giaTien, String ngayTao, String ngayCapNhat) {
		super();
		this.id = id;
		this.tenSanPham = tenSanPham;
		this.maSanPham = maSanPham;
		this.giaTien = giaTien;
		this.ngayTao = ngayTao;
		this.ngayCapNhat = ngayCapNhat;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public String getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public int getGiaTien() {
		return giaTien;
	}

	public void setGiaTien(int giaTien) {
		this.giaTien = giaTien;
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
