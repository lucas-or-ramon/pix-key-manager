package dev.canoa.pixkeymanager.domain.model;

import lombok.Builder;

@Builder
public record PixKey(
        String id,
        KeyType keyType,
        String keyValue,
        AccountType accountType,
        Integer branchNumber,
        Integer accountNumber,
        String accountHolderName,
        String accountHolderLastName,
        String inclusionDateTime,
        String deactivationDateTime
) {

    public boolean isKeyValueValid() {
        return isKeyTypeValid() && keyType.isValid(keyValue);
    }

    public boolean isKeyTypeValid() {
        return keyType != null;
    }

    public boolean isAccountTypeValid() {
        return accountType != null;
    }

    public boolean isAccountNumberValid() {
        return accountNumber.toString().length() <= 8;
    }

    public boolean isBranchNumberValid() {
        return branchNumber.toString().length() <= 4;
    }

    public boolean isAccountHolderNameValid() {
        return accountHolderName.length() <= 30;
    }

    public boolean isAccountHolderLastNameValid() {
        return accountHolderLastName == null || accountHolderLastName.length() <= 45;
    }

    public boolean isInclusionDateTimeValid() {
        return inclusionDateTime != null;
    }
}
