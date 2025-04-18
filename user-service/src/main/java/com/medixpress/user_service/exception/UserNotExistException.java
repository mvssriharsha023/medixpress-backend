package com.medixpress.user_service.exception;

public class UserNotExistException extends MedixpressException {
    public UserNotExistException(String message) {
        super(message);
    }
}
