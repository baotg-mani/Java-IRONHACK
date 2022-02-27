package com.devcamp.task77.model;

import java.util.Date;
import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.*;
import org.springframework.data.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "vouchers")
public class CVoucher {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  @NotNull(message = "phải nhập mã giảm giá")
  @Size(min = 2, message = "mã giảm giá phải có tối thiểu 2 ký tự")
  @Column(name = "maVoucher", unique = true)
  private String maVoucher;
  
  @NotEmpty(message = "phải nhập giá trị phần trăm giảm giá")
  @Column(name = "phanTramGiamGia")
  private String phanTramGiamGia;
  
  @Column(name = "ghiChu")
  private String ghiChu;
  
  @Temporal(TemporalType.DATE)
  @CreatedDate
  @Column(name = "ngayTao", updatable = false)
  @JsonFormat(pattern = "dd-MM-yyyy")
  private Date ngayTao;
  
  @Column(name = "ngayCapNhat")
  @Temporal(TemporalType.DATE)
  @LastModifiedDate
  @JsonFormat(pattern = "dd-MM-yyyy")
  private Date ngayCapNhat;

  public CVoucher() {
    super();
    // TODO Auto-generated constructor stub
  }

  public CVoucher(String paramMaVoucher, String paramPhanTramGiamGia, String paramGhiChu, Date paramNgayTao,
      Date paramNgayCapNhat) {
    super();
    this.maVoucher = paramMaVoucher;
    this.phanTramGiamGia = paramPhanTramGiamGia;
    this.ghiChu = paramGhiChu;
    this.ngayTao = paramNgayTao;
    this.ngayCapNhat = paramNgayCapNhat;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getMaVoucher() {
    return maVoucher;
  }

  public void setMaVoucher(String maVoucher) {
    this.maVoucher = maVoucher;
  }

  public String getPhanTramGiamGia() {
    return phanTramGiamGia;
  }

  public void setPhanTramGiamGia(String phanTramGiamGia) {
    this.phanTramGiamGia = phanTramGiamGia;
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
