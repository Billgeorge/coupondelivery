package com.mercadolibre.coupon.delivery.model;

import java.util.List;

public class ItemsCalculated {

    public ItemsCalculated(List<String> items, Float amount){
        this.items = items;
        this.amountToSpend = amount;
    }
    private List<String> items;
    private Float amountToSpend;

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public Float getAmountToSpend() {
        return amountToSpend;
    }

    public void setAmountToSpend(Float amountToSpend) {
        this.amountToSpend = amountToSpend;
    }

    @Override
    public String toString() {
        return "ItemsCalculated{" +
                "items=" + items +
                ", amountToSpend=" + amountToSpend +
                '}';
    }
}
