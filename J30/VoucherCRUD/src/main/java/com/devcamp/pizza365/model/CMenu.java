package com.devcamp.pizza365.model;

import javax.persistence.*;

@Entity
@Table(name = "p_menu")
public class CMenu {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long menu_id;
	
	@Column(name = "size")
	private char size;
	
	@Column(name = "duong_kinh")
	private int duongKinh;
	
	@Column(name = "suon")
	private int suon;
	
	@Column(name = "salad")
	private int salad;
	
	@Column(name = "so_luong_nuoc_ngot")
	private int soLuongNuocNgot;
	
	@Column(name = "don_gia")
	private long donGia;
	
	public CMenu() {
		
	}
	
	public CMenu(char size, int duongKinh, int suon, int salad, int soLuongNuocNgot,long donGia) {
		this.size = size;
		this.duongKinh = duongKinh;
		this.suon = suon;
		this.salad = salad;
		this.soLuongNuocNgot = soLuongNuocNgot;
		this.donGia = donGia;
	}

	public long getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(long menu_id) {
		this.menu_id = menu_id;
	}

	public char getSize() {
		return size;
	}

	public void setSize(char size) {
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

	public long getDonGia() {
		return donGia;
	}

	public void setDonGia(long donGia) {
		this.donGia = donGia;
	}
}
