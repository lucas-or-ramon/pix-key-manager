package dev.canoa.pixkeymanager.application.exception;

public class NotFoundPixKeyException extends IllegalArgumentException {
    public NotFoundPixKeyException(String message) {
        super(message);
    }
}
