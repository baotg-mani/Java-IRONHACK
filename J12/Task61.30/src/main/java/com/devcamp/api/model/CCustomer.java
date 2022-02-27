package com.devcamp.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class CCustomer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "ho_ten")
	private String hoTen;

	@Column(name = "email")
	private String email;

	@Column(name = "sdt")
	private String soDienThoai;
	
	@Column(name = "dia_chi")
	private String diaChi;

	@Column(name = "ngay_tao")
	private String ngayTao;

	@Column(name = "ngay_cap_nhat")
	private String ngayCapNhat;

	public CCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CCustomer(long id, String hoTen, String email, String soDienThoai, String diaChi, String ngayTao,
			String ngayCapNhat) {
		super();
		this.id = id;
		this.hoTen = hoTen;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
		this.ngayTao = ngayTao;
		this.ngayCapNhat = ngayCapNhat;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
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
