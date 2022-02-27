package com.devcamp.pizza365.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "vouchers")
public class CVoucher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "Phải có mã giảm giá")
    @Size(min = 3, message = "Mã voucher phải có ít nhất 3 ký tự ")
    @Column(name = "ma_voucher", unique = true)
    private String maVoucher;

    @NotEmpty(message = "Nhập giá trị giảm giá")
    @Column(name = "phan_tram_giam_gia")
    private String phanTramGiamGia;

    @Column(name = "ghi_chu")
    private String ghiChu;
    
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
    
    public CVoucher() {
    };

    public CVoucher(String maVoucher, String phanTramGiamGia, String ghiChu, Date ngay_tao, Date ngay_cap_nhat ) {
        this.maVoucher = maVoucher;
        this.phanTramGiamGia = phanTramGiamGia;
        this.ghiChu = ghiChu;
        this.ngayTao = ngay_tao;
        this.ngayCapNhat = ngay_cap_nhat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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