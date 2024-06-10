package dev.canoa.pixkeymanager.application.key;

public record RandomKey(BaseKey baseKey) implements Key {

    @Override
    public KeyType getType() {
        return KeyType.RANDOM;
    }

    @Override
    public String getValue() {
        return baseKey.value();
    }

    @Override
    public boolean isValid() {
        return baseKey.value().matches("^[a-zA-Z0-9]{1,36}$");
    }
}