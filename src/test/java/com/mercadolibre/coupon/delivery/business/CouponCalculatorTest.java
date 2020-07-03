package com.mercadolibre.coupon.delivery.business;

import com.mercadolibre.coupon.delivery.model.ItemsCalculated;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CouponCalculatorTest {

    private CouponCalculator couponCalculator = new CouponCalculator();

    @Test
    public void exampleExerciseEscenarioTest() {
        Map<String, Float> entryMap = new HashMap<String, Float>();
        entryMap.put("ML1",Float.valueOf(100));
        entryMap.put("ML2",Float.valueOf(210));
        entryMap.put("ML3",Float.valueOf(260));
        entryMap.put("ML4",Float.valueOf(80));
        entryMap.put("ML5",Float.valueOf(90));
        List expectedList = new ArrayList<String>();
        expectedList.add("ML2");
        expectedList.add("ML1");
        expectedList.add("ML4");
        expectedList.add("ML5");

        ItemsCalculated items = couponCalculator.calculate(entryMap, Float.valueOf(500));

        assertEquals(expectedList,items.getItems());
        assertEquals(Float.valueOf(480), items.getAmountToSpend());
    }

    @Test
    public void exampleSecondCaseTest() {
        Map<String, Float> entryMap = new HashMap();
        entryMap.put("ML10",Float.valueOf(100));
        entryMap.put("ML33",Float.valueOf(210));
        entryMap.put("ML3456",Float.valueOf(260));
        entryMap.put("ML6789",Float.valueOf(80));
        entryMap.put("ML1234556",Float.valueOf(50));
        entryMap.put("ML12234556",Float.valueOf(70));
        entryMap.put("ML12344556",Float.valueOf(150));
        List expectedList = new ArrayList<String>();
        expectedList.add("ML1234556");
        expectedList.add("ML3456");
        expectedList.add("ML12234556");
        expectedList.add("ML10");

        ItemsCalculated items = couponCalculator.calculate(entryMap, Float.valueOf(500));

        assertEquals(expectedList,items.getItems());
        assertEquals(Float.valueOf(480),items.getAmountToSpend());
    }

    @Test
    public void exampleThirdCaseTest() {
        Map<String, Float> entryMap = new HashMap();
        entryMap.put("ML10",Float.valueOf(10));
        entryMap.put("ML33",Float.valueOf(20));
        entryMap.put("ML3456",Float.valueOf(30));
        entryMap.put("ML6789",Float.valueOf(40));
        entryMap.put("ML1234556",Float.valueOf(60));
        entryMap.put("ML12234556",Float.valueOf(70));

        List expectedList = new ArrayList<String>();
        expectedList.add("ML33");
        expectedList.add("ML3456");

        ItemsCalculated items = couponCalculator.calculate(entryMap, Float.valueOf(50));

        assertEquals(expectedList,items.getItems());
        assertEquals(Float.valueOf(50),items.getAmountToSpend());
    }

    @Test
    public void pricesWithDecimalsTest() {
        Map<String, Float> entryMap = new HashMap();
        entryMap.put("ML10",Float.valueOf("111.22"));
        entryMap.put("ML33",Float.valueOf("220.56"));
        entryMap.put("ML3456",Float.valueOf("300"));
        entryMap.put("ML6789",Float.valueOf("400.23"));
        entryMap.put("ML1234556",Float.valueOf("160"));
        entryMap.put("ML12234556",Float.valueOf("70"));

        List expectedList = new ArrayList();
        expectedList.add("ML10");
        expectedList.add("ML33");
        expectedList.add("ML6789");
        expectedList.add("ML1234556");
        expectedList.add("ML3456");
        ItemsCalculated items = couponCalculator.calculate(entryMap, Float.valueOf(1200));

        assertEquals(expectedList,items.getItems());
        assertEquals(Float.valueOf("1192.01"),items.getAmountToSpend());

    }

    @Test
    public void emptyFavoriteItemsTest() {
        Map<String, Float> entryMap = new HashMap();

        ItemsCalculated items = couponCalculator.calculate(entryMap, Float.valueOf(1200));

        assertTrue(items.getItems().isEmpty());
        assertEquals(Float.valueOf(0),items.getAmountToSpend());

    }

    @Test
    public void nullFavoriteItemsTest() {

        ItemsCalculated items = couponCalculator.calculate(null, Float.valueOf(1200));

        assertTrue(items.getItems().isEmpty());
        assertEquals(Float.valueOf(0),items.getAmountToSpend());

    }

    @Test
    public void zeroAmountTest() {

        Map<String, Float> entryMap = new HashMap();
        entryMap.put("ML10",Float.valueOf("111.22"));
        entryMap.put("ML33",Float.valueOf("220.56"));
        entryMap.put("ML3456",Float.valueOf("300"));
        entryMap.put("ML6789",Float.valueOf("400.23"));
        entryMap.put("ML1234556",Float.valueOf("160"));
        entryMap.put("ML12234556",Float.valueOf("70"));

        ItemsCalculated items = couponCalculator.calculate(null, Float.valueOf(0));

        assertTrue(items.getItems().isEmpty());
        assertEquals(Float.valueOf(0),items.getAmountToSpend());

    }

}