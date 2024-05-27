package dev.canoa.pixkeymanager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;


@AllArgsConstructor
@Validated
@Getter
public enum KeyType {

    CPF("cpf"),
    CNPJ("cnpj"),
    EMAIL("email"),
    PHONE("celular"),
    RANDOM("aleatorio");

    private final String type;

    public static KeyType from(String type) {
        if (type == null) {
            return null;
        }
        for (KeyType keyType : KeyType.values()) {
            if (keyType.getType().equals(type)) {
                return keyType;
            }
        }
        throw new IllegalArgumentException("Tipo de chave inválido");
    }

    public boolean isValid(String key) {
        if (key == null) {
            return false;
        }

        if (this == CPF) {
            return key.matches("^[0-9]{11}$");
        } else if (this == CNPJ) {
            return key.matches("^[0-9]{14}$");
        } else if (this == EMAIL) {
            return key.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        } else if (this == PHONE) {
            return key.matches("^\\+[0-9]{2}[0-9]{3}[0-9]{9}$");
        } else if (this == RANDOM) {
            return key.matches("^[a-zA-Z0-9]{1,36}$");
        } else {
            return false;
        }
    }
}
