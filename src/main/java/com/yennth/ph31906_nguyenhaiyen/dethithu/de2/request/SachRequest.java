package com.yennth.ph31906_nguyenhaiyen.dethithu.de2.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SachRequest {

    private Integer id;

    @NotBlank(message = "Nhap ma")
    private String ma;

    @NotBlank(message = "Nhap ten")
    private String ten;

    @NotNull(message = "Nhap so trang")
    private Integer soTrang;

    @NotNull(message = "Nhap don gia")
    private BigDecimal donGia;

    @NotNull(message = "Nhap id nha xuat ban")
    private Integer idNhaXuatBan;

    @NotNull(message = "Nhap trang thai")
    private Integer trangThai;
}
