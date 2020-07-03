package com.mercadolibre.coupon.delivery.rest;

import com.mercadolibre.coupon.delivery.exception.BadRequestException;
import com.mercadolibre.coupon.delivery.exception.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ResponseExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ResponseExceptionHandler.class);

    @ExceptionHandler(value= BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(BadRequestException ex, WebRequest request) {
        String messageBody = "Bad Request Exception "+ex.getMessage();
        logger.error(ex.getError()+" Message : "+ex.getMessage());
        return ResponseEntity.badRequest().body(messageBody);
    }

    @ExceptionHandler(value= ClientException.class)
    public ResponseEntity<Object> handleClientException(BadRequestException ex, WebRequest request) {
        String messageBody = "Error of communication with external component";
        logger.error(ex.getError()+" Message : "+ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageBody);
    }

    @ExceptionHandler(value= Exception.class)
    public ResponseEntity<Object> handleClientException(Exception ex, WebRequest request) {
        String messageBody = "An error Happened, please contact technical manager";
        logger.error("Message : "+ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageBody);
    }
}
