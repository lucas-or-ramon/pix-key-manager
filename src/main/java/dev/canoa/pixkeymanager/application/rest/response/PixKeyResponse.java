package dev.canoa.pixkeymanager.application.rest.response;

import dev.canoa.pixkeymanager.domain.model.PixKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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
                .keyType(pixKey.key().type().getType())
                .keyValue(pixKey.key().value())
                .accountType(pixKey.account().type().getType())
                .accountNumber(pixKey.account().number())
                .branchNumber(pixKey.account().branch())
                .accountHolderName(pixKey.account().holderName())
                .accountHolderLastName(Objects.requireNonNullElse(pixKey.account().holderLastName(), ""))
                .inclusionDateTime(pixKey.inclusionDateTime().toString())
                .build();
    }

    public static List<PixKeyResponse> fromDomain(List<PixKey> pixKeys) {
        return pixKeys.stream()
                .map(PixKeyResponse::fromDomain)
                .toList();
    }
}
