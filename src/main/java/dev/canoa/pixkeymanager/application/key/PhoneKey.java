package dev.canoa.pixkeymanager.application.key;

public record PhoneKey(BaseKey baseKey) implements Key {

    @Override
    public KeyType getType() {
        return KeyType.PHONE;
    }

    @Override
    public String getValue() {
        return baseKey.value();
    }

    @Override
    public boolean isValid() {
        return baseKey.value().matches("^\\+[0-9]{2}[0-9]{3}[0-9]{9}$");
    }
}