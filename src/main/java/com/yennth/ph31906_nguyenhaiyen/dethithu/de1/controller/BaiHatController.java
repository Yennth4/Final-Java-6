package com.yennth.ph31906_nguyenhaiyen.dethithu.de1.controller;

import com.yennth.ph31906_nguyenhaiyen.dethithu.de1.entity.BaiHat;
import com.yennth.ph31906_nguyenhaiyen.dethithu.de1.response.BaiHatResponse;
import com.yennth.ph31906_nguyenhaiyen.dethithu.de1.service.BaiHatRepository;
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

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/de1/bai-hat/")
public class BaiHatController {

    @Autowired
    public BaiHatRepository baiHatRepository;

    @GetMapping()
    public List<BaiHatResponse> getAll() {
        return baiHatRepository.listBaiHatResponse();
    }

    @GetMapping("phan-trang")
    public List<BaiHatResponse> phanTrang(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page) {
        Pageable pageable = PageRequest.of(page, 2);
        return baiHatRepository.listBaiHatResponse(pageable);
    }

    @GetMapping("detail/{id}")
    public BaiHat detail(@PathVariable Long id) {
        return baiHatRepository.findById(id).get();
    }

    @DeleteMapping("delete")
    public String delete(@RequestParam Long id) {
        baiHatRepository.deleteById(id);
        return "Xoa thanh cong id " + id;
    }

    @PostMapping("add")
    public BaiHat add(@RequestBody BaiHat baiHat) {
        return baiHatRepository.save(baiHat);
    }

    @PutMapping("update")
    public BaiHat update(@RequestBody BaiHat baiHat) {
        return baiHatRepository.save(baiHat);
    }

    @GetMapping("search")
    public List<BaiHatResponse> search(
            @RequestParam(value = "tenCaSi", required = false) String tenCaSi,
            @RequestParam(value = "giaMin", required = false) Double giaMin,
            @RequestParam(value = "giaMax", required = false) Double giaMax) {
        return baiHatRepository.findByCaSiAndGiaRange(tenCaSi, giaMin, giaMax);
    }

    @GetMapping("most-expensive-longest")
    public BaiHat findMostExpensiveAndLongestSong() {
        List<BaiHat> baiHatList = baiHatRepository.findAll();
        return baiHatList.stream()
                .filter(baiHat -> baiHat.getGia() != null && baiHat.getThoiLuong() != null)
                .max(Comparator.comparing(BaiHat::getGia, Comparator.nullsLast(Float::compareTo))
                        .thenComparing(BaiHat::getThoiLuong, Comparator.nullsLast(Integer::compareTo)))
                .orElse(null);
    }


    @GetMapping("/group-by-ca-si")
    public Map<String, Integer> groupSongsByCaSiAndCalculateTotalDuration() {
        return baiHatRepository.findAll()
                .stream()
                .filter(baiHat -> baiHat.getCaSi() != null)
                .collect(Collectors.groupingBy(
                        baiHat -> baiHat.getCaSi().getTenCaSi(),
                        Collectors.summingInt(BaiHat::getThoiLuong)
                ));
    }


}
