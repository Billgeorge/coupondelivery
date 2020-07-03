package com.mercadolibre.coupon.delivery.rest;

import com.mercadolibre.coupon.delivery.business.CouponService;
import com.mercadolibre.coupon.delivery.exception.BadRequestException;
import com.mercadolibre.coupon.delivery.exception.GeneralException;
import com.mercadolibre.coupon.delivery.model.GetItemsForCouponRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CouponControllerTest {

    private CouponController couponController;
    private CouponService couponService;

    @BeforeEach
    void setUp() {
        couponService = mock(CouponService.class);
        couponController = new CouponController(couponService);
    }

    @Test
    public void sendingNullRequest(){
        assertThrows(BadRequestException.class,()->{
            couponController.getItemsForCoupon(null);
        });
    }

    @Test
    public void sendingMoreThanTwoDecimals(){
        Set<String> itemsSet = new HashSet();
        itemsSet.add("item1");
        GetItemsForCouponRequest request = new GetItemsForCouponRequest(itemsSet,Float.valueOf("11.123"));
        assertThrows(BadRequestException.class,()->{
            couponController.getItemsForCoupon(request);
        });
    }

    @Test
    public void sendingEmptySet(){
        Set<String> itemsSet = new HashSet();
        GetItemsForCouponRequest request = new GetItemsForCouponRequest(itemsSet,Float.valueOf("11.12"));
        assertThrows(BadRequestException.class,()->{
            couponController.getItemsForCoupon(request);
        });
    }

    @Test
    public void sendingNullSet(){
        GetItemsForCouponRequest request = new GetItemsForCouponRequest(null,Float.valueOf("11.12"));
        assertThrows(BadRequestException.class,()->{
            couponController.getItemsForCoupon(request);
        });
    }

    @Test
    public void sendingNullAmount(){
        Set<String> itemsSet = new HashSet();
        itemsSet.add("item1");
        GetItemsForCouponRequest request = new GetItemsForCouponRequest(itemsSet,null);
        assertThrows(BadRequestException.class,()->{
            couponController.getItemsForCoupon(request);
        });
    }

    @Test
    public void sendingGoodParam() {
        Set<String> itemsSet = new HashSet();
        itemsSet.add("item1");
        Float amount = Float.valueOf("11.12");
        GetItemsForCouponRequest request = new GetItemsForCouponRequest(itemsSet, amount);
        try {
            couponController.getItemsForCoupon(request);
            verify(couponService, times(1)).calculateItemsForCoupon(itemsSet, amount);
        } catch (Exception e) {
            fail("exception during test");
        }
    }
}