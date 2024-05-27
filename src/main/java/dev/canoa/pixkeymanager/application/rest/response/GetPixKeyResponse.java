package dev.canoa.pixkeymanager.application.rest.response;

import dev.canoa.pixkeymanager.domain.model.PixKey;
import lombok.Builder;

@Builder
public record GetPixKeyResponse(String id, String keyType, String keyValue, String accountType, String branchNumber,
                                String accountNumber, String accountHolderName, String accountHolderLastName,
                                String inclusionDateTime, String deactivationDateTime) {
    public static GetPixKeyResponse fromDomain(PixKey pixKey) {
        return GetPixKeyResponse.builder()
                .id(pixKey.id())
                .keyType(pixKey.keyType().getType())
                .keyValue(pixKey.keyValue())
                .accountType(pixKey.accountType().getType())
                .branchNumber(pixKey.branchNumber().toString())
                .accountNumber(pixKey.accountNumber().toString())
                .accountHolderName(pixKey.accountHolderName())
                .accountHolderLastName(pixKey.accountHolderLastName())
                .inclusionDateTime(pixKey.inclusionDateTime())
                .deactivationDateTime(pixKey.deactivationDateTime())
                .build();
    }
}
