package com.devcamp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu_pizza")
public class CMenu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "size_combo")
	private String size;
	
	@Column(name = "duong_kinh")
	private int duongKinh;
	
	@Column(name = "suon")
	private int suon;
	
	@Column(name = "salad")
	private int salad;
	
	@Column(name = "so_luong_nuoc")
	private int soLuongNuocNgot;
	
	@Column(name = "don_gia")
	private int donGia;

	public CMenu(long id, String size, int duongKinh, int suon, int salad, int soLuongNuocNgot, int donGia) {
		super();
		this.id = id;
		this.size = size;
		this.duongKinh = duongKinh;
		this.suon = suon;
		this.salad = salad;
		this.soLuongNuocNgot = soLuongNuocNgot;
		this.donGia = donGia;
	}

	public CMenu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getDuongKinh() {
		return duongKinh;
	}

	public void setDuongKinh(int duongKinh) {
		this.duongKinh = duongKinh;
	}

	public int getSuon() {
		return suon;
	}

	public void setSuon(int suon) {
		this.suon = suon;
	}

	public int getSalad() {
		return salad;
	}

	public void setSalad(int salad) {
		this.salad = salad;
	}

	public int getSoLuongNuocNgot() {
		return soLuongNuocNgot;
	}

	public void setSoLuongNuocNgot(int soLuongNuocNgot) {
		this.soLuongNuocNgot = soLuongNuocNgot;
	}

	public int getDonGia() {
		return donGia;
	}

	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}
}
