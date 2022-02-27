package com.devcamp.pizza365.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "p_orders")
public class COrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "order_code", unique = true)
	private String orderCode;

	@Column(name = "kich_co")
	private String kichCo;

	@Column(name = "duong_kinh")
	private String duongKinh;

	@Column(name = "suon")
	private int suon;

	@Column(name = "salad")
	private String salad;

	@Column(name = "loai_pizza")
	private String loaiPizza;

	@Column(name = "id_vourcher")
	private String idVourcher;

	@Column(name = "thanh_tien")
	private int thanhTien;

	@Column(name = "giam_gia")
	private int giamGia;

	@Column(name = "id_loai_nuoc_uong")
	private String idLoaiNuocUong;

	@Column(name = "so_luong_nuoc")
	private int soLuongNuoc;

	@Column(name = "ho_ten")
	private String hoTen;

	@Column(name = "email")
	private String email;

	@Column(name = "so_dien_thoai")
	private String soDienThoai;

	@Column(name = "dia_chi")
	private String diaChi;

	@Column(name = "loi_nhan")
	private String loiNhan;

	@Column(name = "ngay_tao")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date ngayTao;

	@Column(name = "ngay_cap_nhat")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date ngayCapNhat;

	@ManyToOne
	@JsonIgnore
	private CUser user;

	@Transient
	private String username;

	@JsonIgnore
	public CUser getUser() {
		return user;
	}

	public void setUser(CUser user) {
		this.user = user;
	}

	@JsonIgnore
	public String getUsername() {
		return getUser().getUsername();
	}

	public void setUsername(String username) {
		this.username = username;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String ordercode) {
		this.orderCode = ordercode;
	}

	public String getKichCo() {
		return kichCo;
	}

	public void setKichCo(String kichCo) {
		this.kichCo = kichCo;
	}

	public String getDuongKinh() {
		return duongKinh;
	}

	public void setDuongKinh(String duongKinh) {
		this.duongKinh = duongKinh;
	}

	public int getSuon() {
		return suon;
	}

	public void setSuon(int suon) {
		this.suon = suon;
	}

	public String getSalad() {
		return salad;
	}

	public void setSalad(String salad) {
		this.salad = salad;
	}

	public String getLoaiPizza() {
		return loaiPizza;
	}

	public void setLoaiPizza(String loaiPizza) {
		this.loaiPizza = loaiPizza;
	}

	public String getIdVourcher() {
		return idVourcher;
	}

	public void setIdVourcher(String idVourcher) {
		this.idVourcher = idVourcher;
	}

	public int getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(int thanhTien) {
		this.thanhTien = thanhTien;
	}

	public int getGiamGia() {
		return giamGia;
	}

	public void setGiamGia(int giamGia) {
		this.giamGia = giamGia;
	}

	public String getIdLoaiNuocUong() {
		return idLoaiNuocUong;
	}

	public void setIdLoaiNuocUong(String idLoaiNuocUong) {
		this.idLoaiNuocUong = idLoaiNuocUong;
	}

	public int getSoLuongNuoc() {
		return soLuongNuoc;
	}

	public void setSoLuongNuoc(int soLuongNuoc) {
		this.soLuongNuoc = soLuongNuoc;
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

	public String getLoiNhan() {
		return loiNhan;
	}

	public void setLoiNhan(String loiNhan) {
		this.loiNhan = loiNhan;
	}

}
