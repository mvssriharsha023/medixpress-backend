package com.medixpress.order_service.exception;

public class CartEmptyException extends MedixpressException {
    public CartEmptyException(String message) {
        super(message);
    }
}
