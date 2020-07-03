package com.mercadolibre.coupon.delivery.business;


import com.mercadolibre.coupon.delivery.model.ItemsCalculated;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Map;


@Component
public class CouponCalculator {

    public ItemsCalculated calculate(Map<String, Float> items, Float amount){
        Float acumValue = Float.valueOf(0);
        List<String> result = new ArrayList();

        if(items==null || items.isEmpty() || amount == Float.valueOf(0)){
            return new ItemsCalculated(result,acumValue);
        }
        String[] nextList = new String[items.size()];
        for(int i = 0; i < items.size(); i++){

            List<String> resultPerIteration = new ArrayList();
            Float sumPerIteration = Float.valueOf(0);
            List<String> keyList = new ArrayList((nextList[items.size()-1]==null)?items.keySet():Arrays.asList(nextList));

            sumPerIteration = iterateEachCase(items, amount, nextList, resultPerIteration, sumPerIteration, keyList);
            if(sumPerIteration > acumValue){
                acumValue = sumPerIteration;
                result=resultPerIteration;
            }
        }
        return new ItemsCalculated(result,acumValue);
    }

    private Float iterateEachCase(Map<String, Float> items, Float amount, String[] nextList, List<String> resultPerIteration, Float sumPerIteration, List<String> keyList) {
        for (int j = 0; j < items.size(); j++) {
            String key = keyList.get(j);
            Float actualValue = items.get(key);
            buildingNextList(items.size(), nextList, j, key);
            if (sumPerIteration + actualValue > amount) {
                continue;
            }
            sumPerIteration += actualValue;
            resultPerIteration.add(key);
        }
        return sumPerIteration;
    }

    private void buildingNextList(int size, String[] nextList, int j, String key) {
        if (j == 0) {
            nextList[size - 1] = key;
        } else {
            nextList[j - 1] = key;
        }
    }
}
