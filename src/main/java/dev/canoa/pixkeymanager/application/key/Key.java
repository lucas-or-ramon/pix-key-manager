package dev.canoa.pixkeymanager.application.key;


public interface Key {
    KeyType getType();

    String getValue();

    boolean isValid();
}
