package com.mercadolibre.coupon.delivery.rest;

import com.mercadolibre.coupon.delivery.exception.BadRequestException;
import com.mercadolibre.coupon.delivery.exception.GeneralException;
import com.mercadolibre.coupon.delivery.util.NumberUtil;
import com.mercadolibre.coupon.delivery.business.CouponService;
import com.mercadolibre.coupon.delivery.exception.ClientException;
import com.mercadolibre.coupon.delivery.model.GetItemsForCouponRequest;
import com.mercadolibre.coupon.delivery.model.ItemsCalculated;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    private CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    /**
     * Only for testing, maybe health check in other scenario
     */
    @GetMapping
    public String get() {
        return "Test";
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ItemsCalculated getItemsForCoupon(@RequestBody GetItemsForCouponRequest request) throws Exception {
        try {
            if (request==null || request.getAmount()==null ||
                    NumberUtil.getNumberOfDecimalOfFloat(request.getAmount()) > 2
                    || request.getItemIds()==null || request.getItemIds().isEmpty()) {
                throw new BadRequestException("Send maximun 2 decimals for amount, and avoid null or empty set in request");
            }
            return couponService.calculateItemsForCoupon(request.getItemIds(), request.getAmount());
        } catch (ClientException e) {
            throw e;
        }catch(BadRequestException e){
            throw  e;
        }catch (Exception e) {
            throw new GeneralException("error executing getItemsForCoupon", e.getMessage(), e);
        }
    }
}
