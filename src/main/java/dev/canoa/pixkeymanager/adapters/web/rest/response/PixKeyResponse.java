package dev.canoa.pixkeymanager.adapters.web.rest.response;

import dev.canoa.pixkeymanager.application.PixKey;
import lombok.Builder;

import java.util.List;
import java.util.Objects;

@Builder
public record PixKeyResponse(
        String id,
        String keyType,
        String keyValue,
        String accountType,
        int branchNumber,
        int accountNumber,
        String accountHolderName,
        String accountHolderLastName,
        String inclusionDateTime
) {

    public static PixKeyResponse fromDomain(PixKey pixKey) {
        return PixKeyResponse.builder()
                .id(pixKey.id())
                .keyType(pixKey.key().getType().getType())
                .keyValue(pixKey.key().getValue())
                .accountType(pixKey.account().getType().getType())
                .accountNumber(pixKey.account().getNumber())
                .branchNumber(pixKey.account().getBranch())
                .accountHolderName(pixKey.account().getHolderName())
                .accountHolderLastName(Objects.requireNonNullElse(pixKey.account().getHolderLastName(), ""))
                .inclusionDateTime(pixKey.inclusionDateTime().toString())
                .build();
    }

    public static List<PixKeyResponse> fromDomain(List<PixKey> pixKeys) {
        return pixKeys.stream()
                .map(PixKeyResponse::fromDomain)
                .toList();
    }
}
