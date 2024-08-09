package com.yennth.ph31906_nguyenhaiyen.dethithu.de2.controller;

import com.yennth.ph31906_nguyenhaiyen.dethithu.de2.entity.Sach;
import com.yennth.ph31906_nguyenhaiyen.dethithu.de2.response.SachResponse;
import com.yennth.ph31906_nguyenhaiyen.dethithu.de2.service.SachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/sach/")
public class SachController {

    @Autowired
    private SachService sachService;

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

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        sachService.deleteById(id);
        return "Xoa thanh cong " + id;
    }

    @PostMapping("add")
    public String add(@RequestBody Sach sach) {
        sachService.save(sach);
        return "Them thanh cong";
    }

    @PutMapping("update")
    public String update(@RequestBody Sach sach) {
        sachService.save(sach);
        return "Sua thanh cong";
    }
}
