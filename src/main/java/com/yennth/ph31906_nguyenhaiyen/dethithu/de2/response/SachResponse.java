package com.yennth.ph31906_nguyenhaiyen.dethithu.de2.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SachResponse {

    private String ma;

    private String ten;

    private Integer soTrang;

    private BigDecimal donGia;

    private String tenNxb;
}
