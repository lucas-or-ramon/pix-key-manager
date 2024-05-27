package dev.canoa.pixkeymanager.application.rest.request;

import dev.canoa.pixkeymanager.domain.model.GetPixKey;
import dev.canoa.pixkeymanager.domain.model.KeyType;

public record GetPixKeyRequest(
        String id,
        String keyType,
        Integer branchNumber,
        Integer accountNumber,
        String accountHolderName,
        String inclusionDateTime,
        String deactivationDateTime
) {

    public GetPixKey toDomain() {
        return GetPixKey.builder()
                .id(id)
                .keyType(KeyType.from(keyType))
                .branchNumber(branchNumber)
                .accountNumber(accountNumber)
                .accountHolderName(accountHolderName)
                .inclusionDateTime(inclusionDateTime)
                .deactivationDateTime(deactivationDateTime)
                .build();
    }
}
