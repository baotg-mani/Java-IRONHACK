package com.devcamp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "drinks")
public class CDrink {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "Nhập mã nước uống")
	@Column(name = "ma_nuoc_uong", unique = true)
	private String maNuocUong;

	@NotEmpty(message = "Nhập tên nước uống")
	@Column(name = "ten_nuoc_uong", unique = true)
	private String tenNuocUong;

	@NotNull(message = "Phải có đơn giá")
	@Column(name = "don_gia")
	private long donGia;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ngay_tao", nullable = true, updatable = false)
	@CreatedDate
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date ngayTao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ngay_cap_nhat", nullable = true)
	@LastModifiedDate
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date ngayCapNhat;

	public CDrink() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CDrink(long id, String maNuocUong, String tenNuocUong, int donGia, Date ngayTao, Date ngayCapNhat) {
		super();
		this.id = id;
		this.maNuocUong = maNuocUong;
		this.tenNuocUong = tenNuocUong;
		this.donGia = donGia;
		this.ngayTao = ngayTao;
		this.ngayCapNhat = ngayCapNhat;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getDonGia() {
		return donGia;
	}

	public void setDonGia(long donGia) {
		this.donGia = donGia;
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
