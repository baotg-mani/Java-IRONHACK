package com.devcamp.pizza365.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pizza_order")
public class COrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "order_id", unique = true)
	private String orderId;
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
	@Column(name = "id_voucher")
	private String idVourcher;
	@Column(name = "thanh_tien")
	private int thanhTien;
	@Column(name = "giam_gia")
	private int giamGia;
	@Column(name = "id_nuoc_uong")
	private String idLoaiNuocUong;
	@Column(name = "so_luong_nuoc")
	private int soLuongNuoc;
	@Column(name = "ho_ten")
	private String hoTen;
	@Column(name = "email")
	private String email;
	@Column(name = "phone")
	private String soDienThoai;
	@Column(name = "dia_chi")
	private String diaChi;
	@Column(name = "loi_nhan")
	private String loiNhan;
	@Column(name = "trang_thai")
	private String trangThai;
	@Column(name = "ngay_tao")
	private long ngayTao;
	@Column(name = "ngay_update")
	private long ngayCapNhat;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	// @JsonBackReference
	private CCustomer customer;

	public COrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public COrder(long id, String orderId, String kichCo, String duongKinh, int suon, String salad, String loaiPizza,
			String idVourcher, int thanhTien, int giamGia, String idLoaiNuocUong, int soLuongNuoc, String hoTen,
			String email, String soDienThoai, String diaChi, String loiNhan, String trangThai, long ngayTao, long ngayCapNhat,
			CCustomer customer) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.kichCo = kichCo;
		this.duongKinh = duongKinh;
		this.suon = suon;
		this.salad = salad;
		this.loaiPizza = loaiPizza;
		this.idVourcher = idVourcher;
		this.thanhTien = thanhTien;
		this.giamGia = giamGia;
		this.idLoaiNuocUong = idLoaiNuocUong;
		this.soLuongNuoc = soLuongNuoc;
		this.hoTen = hoTen;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
		this.loiNhan = loiNhan;
		this.trangThai = trangThai;
		this.ngayTao = ngayTao;
		this.ngayCapNhat = ngayCapNhat;
		this.customer = customer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public long getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(int thanhTien) {
		this.thanhTien = thanhTien;
	}

	public long getGiamGia() {
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

	public long getSoLuongNuoc() {
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

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
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

	public CCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(CCustomer customer) {
		this.customer = customer;
	}

}
