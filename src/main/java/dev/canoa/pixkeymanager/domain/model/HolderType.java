package dev.canoa.pixkeymanager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HolderType {
    NATURAL_PERSON("pessoa física"),
    LEGAL_PERSON("pessoa jurídica");

    private final String description;

    public boolean isValidNumberOfKeys(long numberOfKeys) {
        if (this.equals(NATURAL_PERSON)) {
            return numberOfKeys >= 0 && numberOfKeys <= 5;
        } else if (this.equals(LEGAL_PERSON)) {
            return numberOfKeys >= 0 && numberOfKeys <= 20;
        }
        throw new IllegalArgumentException("Tipo de proprietário inválido");
    }
}

