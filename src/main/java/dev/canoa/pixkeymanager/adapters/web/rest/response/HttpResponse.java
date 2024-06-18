package dev.canoa.pixkeymanager.adapters.web.rest.response;

public record HttpResponse<T>(
        T data,
        String message
) {

    public static <T> HttpResponse<T> of(T data, String message) {
        return new HttpResponse<>(data, message);
    }
}
