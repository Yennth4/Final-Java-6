package com.yennth.ph31906_nguyenhaiyen.dethithu.de1.entity;

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
@Table(name = "BaiHat")
public class BaiHat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String tenBaiHat;

    public String tenTacGia;

    public Integer thoiLuong;

    public Date ngaySanXuat;

    public Float gia;

    @ManyToOne
    @JoinColumn(name = "CaSiID", referencedColumnName = "id")
    public CaSi caSi;

    public Boolean phatHanhDia;

    public Date ngayRaMat;

}
