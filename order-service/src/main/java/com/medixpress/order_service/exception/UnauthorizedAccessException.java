package com.medixpress.order_service.exception;

public class UnauthorizedAccessException extends MedixpressException {
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
