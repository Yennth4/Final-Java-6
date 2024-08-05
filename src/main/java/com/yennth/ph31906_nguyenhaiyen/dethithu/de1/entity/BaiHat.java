package com.yennth.ph31906_nguyenhaiyen.dethithu.de1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "bai_hat")
public class BaiHat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "ten_bai_hat")
    public String tenBaiHat;

    @Column(name = "ten_tac_gia")
    public String tenTacGia;

    @Column(name = "thoi_luong")
    public Integer thoiLuong;

    @Column(name = "ngay_san_xuat")
    public Date ngaySanXuat;

    @Column(name = "gia")
    public Float gia;

    @ManyToOne
    @JoinColumn(name = "ca_si_id", referencedColumnName = "id")
    public CaSi caSi;

    @Column(name = "phat_hanh_dia")
    public Boolean phatHanhDia;

    @Column(name = "ngay_ra_mat")
    public Date ngayRaMat;

}
