package com.yennth.ph31906_nguyenhaiyen.dethithu.de2.service;

import com.yennth.ph31906_nguyenhaiyen.dethithu.de2.entity.Sach;
import com.yennth.ph31906_nguyenhaiyen.dethithu.de2.response.SachResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SachService extends JpaRepository<Sach, Integer> {

    @Query(value = """
            SELECT new com.yennth.ph31906_nguyenhaiyen.dethithu.de2.response.SachResponse(s.ma, s.ten, s.soTrang, s.donGia, n.ten)
            FROM Sach s JOIN NXB n ON s.nxb.id = n.id 
            """)
    List<SachResponse> listResponse();

    @Query(value = """
            SELECT new com.yennth.ph31906_nguyenhaiyen.dethithu.de2.response.SachResponse(s.ma, s.ten, s.soTrang, s.donGia, n.ten)
            FROM Sach s JOIN NXB n ON s.nxb.id = n.id 
            """)
    List<SachResponse> listResponse(Pageable pageable);

}
