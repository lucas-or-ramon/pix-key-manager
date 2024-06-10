package dev.canoa.pixkeymanager.application.key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;


@Getter
@Validated
@AllArgsConstructor
public enum KeyType {

    CPF("cpf"), CNPJ("cnpj"), EMAIL("email"), PHONE("celular"), RANDOM("aleatorio");

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
}

