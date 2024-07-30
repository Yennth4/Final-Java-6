package com.yennth.ph31906_nguyenhaiyen.dethithu.de1.service;

import com.yennth.ph31906_nguyenhaiyen.dethithu.de1.entity.BaiHat;
import com.yennth.ph31906_nguyenhaiyen.dethithu.de1.response.BaiHatResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaiHatRepository extends JpaRepository<BaiHat, Long> {

    // list
    @Query(value = """
            SELECT new com.yennth.ph31906_nguyenhaiyen.dethithu.de1.response.BaiHatResponse(b.tenBaiHat, b.tenTacGia, b.thoiLuong, b.ngaySanXuat, b.gia, c.tenCaSi, c.queQuan, b.ngayRaMat)
            FROM BaiHat b JOIN CaSi c ON b.caSi.id = c.id
            """)
    List<BaiHatResponse> listBaiHatResponse();

    // phan-trang
    @Query(value = """
            SELECT new com.yennth.ph31906_nguyenhaiyen.dethithu.de1.response.BaiHatResponse(b.tenBaiHat, b.tenTacGia, b.thoiLuong, b.ngaySanXuat, b.gia, c.tenCaSi, c.queQuan, b.ngayRaMat)
            FROM BaiHat b JOIN CaSi c ON b.caSi.id = c.id
            """)
    List<BaiHatResponse> listBaiHatResponse(Pageable pageable);

    // search
    @Query(value = """
        SELECT new com.yennth.ph31906_nguyenhaiyen.dethithu.de1.response.BaiHatResponse(b.tenBaiHat, b.tenTacGia, b.thoiLuong, b.ngaySanXuat, b.gia, c.tenCaSi, c.queQuan, b.ngayRaMat)
        FROM BaiHat b JOIN CaSi c ON b.caSi.id = c.id
        WHERE (:tenCaSi IS NULL OR c.tenCaSi LIKE %:tenCaSi%) AND
              (:giaMin IS NULL OR b.gia >= :giaMin) AND
              (:giaMax IS NULL OR b.gia <= :giaMax)
        """)
    List<BaiHatResponse> findByCaSiAndGiaRange(String tenCaSi, Double giaMin, Double giaMax);

}
