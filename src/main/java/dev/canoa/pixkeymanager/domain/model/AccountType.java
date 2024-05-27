package dev.canoa.pixkeymanager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
@AllArgsConstructor
public enum AccountType {

    CHECKING("corrente"),
    SAVINGS("poupança");

    private final String type;

    public static AccountType from(String type) {
        for (AccountType accountType : AccountType.values()) {
            if (accountType.getType().equals(type)) {
                return accountType;
            }
        }
        throw new IllegalArgumentException("Tipo de conta inválido");
    }
}
