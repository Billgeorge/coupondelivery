package com.mercadolibre.coupon.delivery.exception;

public class BadRequestException extends GeneralException{

    public BadRequestException(String message) {
        super(message, "BAD REQUEST");
    }
}
