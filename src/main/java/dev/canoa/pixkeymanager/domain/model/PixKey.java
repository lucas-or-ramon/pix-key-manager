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

    public boolean isValid() {
        return isKeyValueValid() &&
                isAccountTypeValid() &&
                isBranchNumberValid() &&
                isAccountNumberValid() &&
                isAccountHolderNameValid() &&
                isAccountHolderLastNameValid() &&
                isInclusionDateTimeValid();
    }

    private boolean isKeyValueValid() {
        return isKeyTypeValid() && keyType.isValid(keyValue);
    }

    private boolean isKeyTypeValid() {
        return keyType != null;
    }

    private boolean isAccountTypeValid() {
        return accountType != null;
    }

    private boolean isAccountNumberValid() {
        return accountNumber.toString().length() <= 8;
    }

    private boolean isBranchNumberValid() {
        return branchNumber.toString().length() <= 4;
    }

    private boolean isAccountHolderNameValid() {
        return accountHolderName.length() <= 30;
    }

    private boolean isAccountHolderLastNameValid() {
        return accountHolderLastName == null || accountHolderLastName.length() <= 45;
    }

    private boolean isInclusionDateTimeValid() {
        return inclusionDateTime != null;
    }
}
