package com.mercadolibre.coupon.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchProductResponse {
    private List<ResultProduct> results;

    public List<ResultProduct> getResults() {
        return results;
    }

    public void setResults(List<ResultProduct> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "SearchProductResponse{" +
                "results=" + results +
                '}';
    }
}
