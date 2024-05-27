package dev.canoa.pixkeymanager.application.rest.response;

import dev.canoa.pixkeymanager.domain.model.PixKey;
import lombok.Builder;

@Builder
public record GetPixKeyResponse(
        String id,
        String keyType,
        String keyValue,
        String accountType,
        int branchNumber,
        int accountNumber,
        String accountHolderName,
        String accountHolderLastName,
        String inclusionDateTime) {

    public static GetPixKeyResponse fromDomain(PixKey pixKey) {
        return GetPixKeyResponse.builder()
                .id(pixKey.id())
                .keyType(pixKey.key().type().getType())
                .keyValue(pixKey.key().value())
                .accountType(pixKey.account().type().getType())
                .accountNumber(pixKey.account().number())
                .branchNumber(pixKey.account().branch())
                .accountHolderName(pixKey.account().holderName())
                .accountHolderLastName(pixKey.account().holderLastName())
                .inclusionDateTime(pixKey.inclusionDateTime())
                .build();
    }
}
