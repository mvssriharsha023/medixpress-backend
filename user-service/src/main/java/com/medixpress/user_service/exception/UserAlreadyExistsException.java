package com.medixpress.user_service.exception;

public class UserAlreadyExistsException extends MedixpressException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
