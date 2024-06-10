package dev.canoa.pixkeymanager.application.key;

public record CpfKey(BaseKey baseKey) implements Key {

    @Override
    public KeyType getType() {
        return KeyType.CPF;
    }

    @Override
    public String getValue() {
        return baseKey.value();
    }

    @Override
    public boolean isValid() {
        return baseKey.value().matches("^[0-9]{11}$");
    }
}
