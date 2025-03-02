package com.ratna.springcloud.couponservice.controllers;

import com.ratna.springcloud.couponservice.models.Coupon;
import com.ratna.springcloud.couponservice.repos.CouponRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/couponapi")
public class CouponRestController {

    private final CouponRepo couponRepo;

    public CouponRestController(CouponRepo couponRepo) {
        this.couponRepo = couponRepo;
    }

    @PostMapping( "/coupons")
    private Coupon create(@RequestBody Coupon coupon) {
        return couponRepo.save(coupon);
    }

    @GetMapping("/coupons/id/{id}")
    private Coupon findById(@PathVariable Long id) {
       return couponRepo.findById(id).get();
    }

    @DeleteMapping("/coupons/id/{id}")
    private void delete(@PathVariable Long id) {
        couponRepo.deleteById(id);
    }

    @GetMapping("/coupons/code/{code}")
    private ResponseEntity<Coupon> findByCode(@PathVariable String code) {
        System.out.println("Server 2");
        return new ResponseEntity<>(couponRepo.findByCode(code), HttpStatus.CREATED);
    }

}
