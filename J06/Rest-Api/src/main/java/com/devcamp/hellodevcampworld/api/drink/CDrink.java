package com.devcamp.hellodevcampworld.api.drink;

import java.util.Date;

public class CDrink {
	private String maNuocUong;
	private String tenNuocUong;
	private int donGia;
	private String ghiChu;
	private Date ngayTao;
	private Date ngayCapNhat;

	public CDrink(String maNuocUong, String tenNuocUong, int donGia, String ghiChu, Date ngayTao, Date ngayCapNhat) {
		super();
		this.maNuocUong = maNuocUong;
		this.tenNuocUong = tenNuocUong;
		this.donGia = donGia;
		this.ghiChu = ghiChu;
		this.ngayTao = ngayTao;
		this.ngayCapNhat = ngayCapNhat;
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

	public int getDonGia() {
		return donGia;
	}

	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public Date getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}

	public Date getNgayCapNhat() {
		return ngayCapNhat;
	}

	public void setNgayCapNhat(Date ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}
	
	

}
