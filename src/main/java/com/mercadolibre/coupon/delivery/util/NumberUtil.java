package com.mercadolibre.coupon.delivery.util;

public class NumberUtil {

    public static int getNumberOfDecimalOfFloat(Float number){
        String[] numberString = number.toString().split("\\.");
        return numberString[1].length();
    }
}
