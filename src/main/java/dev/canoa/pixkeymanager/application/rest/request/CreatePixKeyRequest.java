package dev.canoa.pixkeymanager.application.rest.request;

import dev.canoa.pixkeymanager.domain.model.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CreatePixKeyRequest(
        String keyType,
        String keyValue,
        String accountType,
        Integer branchNumber,
        Integer accountNumber,
        String accountHolderName,
        String accountHolderLastName
) {
    public CreatePixKey toDomain() {
        return CreatePixKey.builder()
                .keyType(KeyType.from(keyType))
                .keyValue(keyValue)
                .accountType(AccountType.from(accountType))
                .branchNumber(branchNumber)
                .accountNumber(accountNumber)
                .accountHolderName(accountHolderName)
                .accountHolderLastName(accountHolderLastName)
                .inclusionDateTime(LocalDateTime.now().toString())
                .build();
    }
}
