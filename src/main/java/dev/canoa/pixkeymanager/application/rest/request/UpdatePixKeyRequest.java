package dev.canoa.pixkeymanager.application.rest.request;

import dev.canoa.pixkeymanager.domain.model.UpdatePixKey;

public record UpdatePixKeyRequest(
        String accountType,
        Integer branchNumber,
        Integer accountNumber,
        String accountHolderName,
        String accountHolderLastName
) {
        public UpdatePixKey toDomain() {
                return UpdatePixKey.builder()
                        .accountType(accountType)
                        .branchNumber(branchNumber)
                        .accountNumber(accountNumber)
                        .accountHolderName(accountHolderName)
                        .accountHolderLastName(accountHolderLastName)
                        .build();
        }
}
