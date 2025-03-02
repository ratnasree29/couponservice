package com.ratna.springcloud.couponservice.controllers;

import com.ratna.springcloud.couponservice.models.Coupon;
import com.ratna.springcloud.couponservice.repos.CouponRepo;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CouponRestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CouponRepo couponRepo;

    @LocalServerPort
    private int port;

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/couponapi/coupons";
    }

    @After
    public void tearDown() {
        couponRepo.deleteAll();
    }

    @Test
    public void testGetAllCoupons() {
        List<Coupon> couponList = Arrays.asList(
                new Coupon("SuperSale", new BigDecimal(11), LocalDate.now().toString()),
                new Coupon("SuperSale", new BigDecimal(10), LocalDate.now().toString()));
        couponRepo.saveAll(couponList);
        baseUrl = baseUrl + "/code/" + couponList.get(0).getCode();
        ResponseEntity<List<Coupon>> response = restTemplate.exchange(
                baseUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().size()).isEqualTo(2);
    }
}