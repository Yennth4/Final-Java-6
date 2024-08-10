package com.yennth.ph31906_nguyenhaiyen.dethithu.de2.controller;

import com.yennth.ph31906_nguyenhaiyen.dethithu.de2.entity.Sach;
import com.yennth.ph31906_nguyenhaiyen.dethithu.de2.request.SachRequest;
import com.yennth.ph31906_nguyenhaiyen.dethithu.de2.response.SachResponse;
import com.yennth.ph31906_nguyenhaiyen.dethithu.de2.service.NXBService;
import com.yennth.ph31906_nguyenhaiyen.dethithu.de2.service.SachService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

@RestController()
@RequestMapping("/sach/")
public class SachController {

    @Autowired
    private SachService sachService;

    @Autowired
    private NXBService nxbService;

    @GetMapping()
    public List<SachResponse> getAll() {
        return sachService.listResponse();
    }

    @GetMapping("phan-trang")
    public List<SachResponse> phanTrang(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page) {
        Pageable pageable = PageRequest.of(page, 2);
        return sachService.listResponse(pageable);
    }

    @GetMapping("detail/{id}")
    public Sach detail(@PathVariable Integer id) {
        return sachService.findById(id).get();
    }

    @DeleteMapping("delete/{ma}")
    public String delete(@PathVariable String ma) {
        sachService.deleteByMa(ma);
        return "Xoa thanh cong " + ma;
    }

    @PostMapping("add")
    public String add(@RequestBody @Valid SachRequest sachRequest, BindingResult result) {

        // bao loi
        if (result.hasErrors()) {
            return "Error " + result.getFieldError().getDefaultMessage();
        }

        // Ma sai dinh dang
        String ma = sachRequest.getMa();
        if (!Pattern.matches("^B\\d{3}$", ma)) {
            return "Them that bai: Ma khong dung dinh dang B001";
        }

        // Ma trung
        Sach exisMa = sachService.findByMa(ma);
        if (exisMa != null && !exisMa.getId().equals(sachRequest.getId())) {
            return "Them that bai: Ma da ton tai";
        }

        Sach sach = new Sach();
        sach.setMa(sachRequest.getMa());
        sach.setTen(sachRequest.getTen());
        sach.setSoTrang(sachRequest.getSoTrang());
        sach.setDonGia(sachRequest.getDonGia());
        sach.setTrangThai(sachRequest.getTrangThai());
        sach.setNxb(nxbService.findById(sachRequest.getIdNhaXuatBan()).get());

        sachService.save(sach);
        return "Them thanh cong";
    }

    @PutMapping("update")
    public String update(@RequestBody SachRequest sachRequest) {

        Sach sach = new Sach();
        sach.setId(sachRequest.getId()); // update phai them id
        sach.setMa(sachRequest.getMa());
        sach.setTen(sachRequest.getTen());
        sach.setSoTrang(sachRequest.getSoTrang());
        sach.setDonGia(sachRequest.getDonGia());
        sach.setTrangThai(sachRequest.getTrangThai());
        sach.setNxb(nxbService.findById(sachRequest.getIdNhaXuatBan()).get());

        sachService.save(sach);
        return "Update thanh cong";
    }

    @GetMapping("loc1")
    public List<SachResponse> listLoc() {
        return sachService.listResponse().stream()
                .filter(item -> item.getTenNxb().equalsIgnoreCase("a"))
                .toList();
    }

    @GetMapping("sap-xep")
    public List<SachResponse> listSapXep() {
        return sachService.listResponse().stream()
                .sorted(Comparator.comparing((SachResponse s) -> {
                    return s.getDonGia();
                }).reversed()) // giam dan; tăng thi kh can them .reversed()
                .toList();
    }

    @GetMapping("loc2")
    public List<SachResponse> listLoc2() {
        return sachService.listResponse().stream()
                .filter(item -> item.getTen().equalsIgnoreCase("a"))
                .filter(item -> item.getSoTrang() >= 10 && item.getSoTrang() <= 20)
                .toList();
    }

}
