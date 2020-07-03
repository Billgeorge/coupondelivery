package com.mercadolibre.coupon.delivery.business;

import com.mercadolibre.coupon.delivery.client.MercadoLibreClient;
import com.mercadolibre.coupon.delivery.exception.ClientException;
import com.mercadolibre.coupon.delivery.model.ItemsCalculated;
import com.mercadolibre.coupon.delivery.model.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.mercadolibre.coupon.delivery.client.MercadoLibreClient.ITEM_URL;

@Service
public class CouponService {

    private CouponCalculator couponCalculator;
    private MercadoLibreClient mercadoLibreClient;


    public CouponService(CouponCalculator couponCalculator, MercadoLibreClient mercadoLibreClient){
        this.mercadoLibreClient = mercadoLibreClient;
        this.couponCalculator = couponCalculator;
    }

    public ItemsCalculated calculateItemsForCoupon(Set<String> items, Float amount) throws ClientException {

        Map<String,Float> itemsMap = new HashMap();
        for (String itemId: items){
            Product product = mercadoLibreClient.executeGet(ITEM_URL+itemId,Product.class);
            itemsMap.put(itemId,product.getPrice());
        }

        return couponCalculator.calculate(itemsMap,amount);
    }

}
