package com.mercadolibre.coupon.delivery.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class GetItemsForCouponRequest {

    @JsonProperty("item_ids")
    private Set<String> itemIds;
    private Float amount;

    public GetItemsForCouponRequest(Set<String> itemIds,Float amount){
        this.amount=amount;
        this.itemIds=itemIds;
    }
    public GetItemsForCouponRequest(){

    }

    public Set<String> getItemIds() {
        return itemIds;
    }

    public void setItemIds(Set<String> itemIds) {
        this.itemIds = itemIds;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "GetItemsForCouponRequest{" +
                "item_ids=" + itemIds +
                ", amount=" + amount +
                '}';
    }
}
