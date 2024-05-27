package dev.canoa.pixkeymanager.application.rest.controller.response;

public class GetPixKeyResponse {
    private final String key;

    public GetPixKeyResponse(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
