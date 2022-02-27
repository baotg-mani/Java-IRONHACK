package com.devcamp.postman.model;
public class CDrink {
	private String maNuocUong;
	private String tenNuocUong;
	private int donGia;
	private String ngayTao;
	private String ngayCapNhat;

	public CDrink(String maNuocUong, String tenNuocUong, int donGia) {
		this.maNuocUong = maNuocUong;
		this.tenNuocUong = tenNuocUong;
		this.donGia = donGia;
		this.setNgayTao("14/5/2021");
		this.setNgayCapNhat("14/5/2021");
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
