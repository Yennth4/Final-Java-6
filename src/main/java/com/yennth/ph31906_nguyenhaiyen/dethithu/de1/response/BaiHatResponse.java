package com.yennth.ph31906_nguyenhaiyen.dethithu.de1.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BaiHatResponse {

    private String tenBaiHat;

    private String tenTacGia;

    private Integer thoiLuong;

    private Date ngaySanXuat;

    private Float gia;

    private String tenCaSi;

    private String queQuan;

    private Date ngayRaMat;

}
