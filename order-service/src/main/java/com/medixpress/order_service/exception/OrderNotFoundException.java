package com.medixpress.order_service.exception;

public class OrderNotFoundException extends MedixpressException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
