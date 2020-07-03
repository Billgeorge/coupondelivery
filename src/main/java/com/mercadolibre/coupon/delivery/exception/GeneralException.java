package com.mercadolibre.coupon.delivery.exception;

public class GeneralException extends Exception{

    private String message;
    private String error;

    public GeneralException(String message, String error){
        super(message);
        this.message= message;
        this.error = error;
    }

    public GeneralException(String message, String error, Throwable cause){
        super(message, cause);
        this.message= message;
        this.error = error;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public String getError() {
        return this.error;
    }
}
