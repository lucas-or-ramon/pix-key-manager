package dev.canoa.pixkeymanager.application.rest.request;

import dev.canoa.pixkeymanager.domain.model.Account;
import dev.canoa.pixkeymanager.domain.model.AccountType;

public record UpdatePixKeyRequest(
        String accountType,
        Integer branchNumber,
        Integer accountNumber,
        String accountHolderName,
        String accountHolderLastName
) {
    public Account toDomain() {
        return Account.builder()
                .type(AccountType.from(accountType))
                .number(accountNumber)
                .branch(branchNumber)
                .holderName(accountHolderName)
                .holderLastName(accountHolderLastName)
                .build();
    }
}
