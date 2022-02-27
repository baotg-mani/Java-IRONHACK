package com.devcamp.hellodevcampworld.api.menu;

public class CMenu {
	private char size;
	private int duongKinh;
	private int suon;
	private int salad;
	private int soLuongNuocNgot;
	private int donGia;
	
	public CMenu(char size, int duongKinh, int suon, int salad, int soLuongNuocNgot, int donGia) {
		this.size = size;
		this.duongKinh = duongKinh;
		this.suon = suon;
		this.salad = salad;
		this.soLuongNuocNgot = soLuongNuocNgot;
		this.donGia = donGia;
	}

	// method getter and setter
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

	public int getDonGia() {
		return donGia;
	}

	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}

	public int getSoLuongNuocNgot() {
		return soLuongNuocNgot;
	}

	public void setSoLuongNuocNgot(int soLuongNuocNgot) {
		this.soLuongNuocNgot = soLuongNuocNgot;
	}

	@Override
	public String toString() {
		return this.size + "{size=" + this.size + ", duongKinh=" + this.duongKinh + ", suon=" + this.suon + ", salad=" + this.salad
				+ ", soLuongNuocNgot=" + this.soLuongNuocNgot + ", donGia=" + this.donGia + "}";
	}
}
