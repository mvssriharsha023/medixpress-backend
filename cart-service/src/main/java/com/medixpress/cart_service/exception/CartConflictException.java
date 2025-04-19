package com.medixpress.cart_service.exception;

public class CartConflictException extends MedixpressException {
    public CartConflictException(String message) {
        super(message);
    }
}
