package dev.canoa.pixkeymanager.application.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountType {

    CHECKING("corrente"),
    SAVINGS("poupança");

    private final String type;

    public static AccountType from(String type) {
        if (type == null) {
            return null;
        }
        for (AccountType accountType : AccountType.values()) {
            if (accountType.getType().equals(type)) {
                return accountType;
            }
        }
        throw new IllegalArgumentException("Tipo de conta inválido");
    }
}
