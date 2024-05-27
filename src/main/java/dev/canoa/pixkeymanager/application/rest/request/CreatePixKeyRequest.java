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
                .key(new Key(KeyType.from(keyType), keyValue))
                .account(Account.builder()
                        .type(AccountType.from(accountType))
                        .branch(branchNumber)
                        .number(accountNumber)
                        .holderName(accountHolderName)
                        .holderLastName(accountHolderLastName)
                        .build())
                .build();
    }
}
