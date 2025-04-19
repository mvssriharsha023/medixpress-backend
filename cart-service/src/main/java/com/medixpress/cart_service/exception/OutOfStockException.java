package com.medixpress.cart_service.exception;

public class OutOfStockException extends MedixpressException {
    public OutOfStockException(String message) {
        super(message);
    }
}
