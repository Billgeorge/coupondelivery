package com.mercadolibre.coupon.delivery.exception;

public class ClientException  extends GeneralException{

    public ClientException(String message, String error) {
        super(message, error);
    }

    public ClientException(String message, String error, Throwable cause) {
        super(message, error, cause);
    }
}
