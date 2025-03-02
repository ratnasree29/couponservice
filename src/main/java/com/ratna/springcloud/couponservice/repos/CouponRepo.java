package com.ratna.springcloud.couponservice.repos;

import com.ratna.springcloud.couponservice.models.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepo extends JpaRepository<Coupon, Long> {
    Coupon findByCode(String code);
}
