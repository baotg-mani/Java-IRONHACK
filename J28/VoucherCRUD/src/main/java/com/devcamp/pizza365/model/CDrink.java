package com.devcamp.pizza365.model;

import javax.persistence.*;

@Entity
@Table(name = "p_drinks")
public class CDrink {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long stt;
	
	@Column(name = "ma_nuoc_uong")
	private String maNuocUong;
	
	@Column(name = "ten_nuoc_uong")
	private String tenNuocUong;
	
	@Column(name = "don_gia")
	private int donGia;
	
	@Column(name = "ngay_tao")
	private long ngayTao;
	
	@Column(name = "ngay_cap_nhat")
	private long ngayCapNhat;
	
	public CDrink() {
		
	}
	
	public CDrink(String maNuocUong, String tenNuocUong, int donGia, long ngay_tao, long ngay_cap_nhat) {
		this.maNuocUong = maNuocUong;
		this.tenNuocUong = tenNuocUong;
		this.donGia = donGia;
		this.ngayTao = ngay_tao;
		this.ngayCapNhat = ngay_cap_nhat;
	}
	
	public long getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
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
