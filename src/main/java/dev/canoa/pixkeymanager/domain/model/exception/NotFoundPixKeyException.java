package dev.canoa.pixkeymanager.domain.model.exception;

public class NotFoundPixKeyException extends IllegalArgumentException {
    public NotFoundPixKeyException(String message) {
        super(message);
    }
}
