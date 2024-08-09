package com.yennth.ph31906_nguyenhaiyen.dethithu.de2.service;

import com.yennth.ph31906_nguyenhaiyen.dethithu.de2.entity.NXB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NXBService extends JpaRepository<NXB, Integer> {

}
