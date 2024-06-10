package dev.canoa.pixkeymanager.application.key;

public record EmailKey(BaseKey baseKey) implements Key {

    @Override
    public KeyType getType() {
        return KeyType.EMAIL;
    }

    @Override
    public String getValue() {
        return baseKey.value();
    }

    @Override
    public boolean isValid() {
        return baseKey.value().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }
}
