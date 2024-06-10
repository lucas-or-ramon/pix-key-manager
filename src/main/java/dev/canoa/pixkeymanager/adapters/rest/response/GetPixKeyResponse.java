package dev.canoa.pixkeymanager.adapters.rest.response;

import dev.canoa.pixkeymanager.application.PixKey;
import lombok.Builder;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;


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
        String inclusionDateTime,
        String deactivationDateTime
){
    public static GetPixKeyResponse fromDomain(PixKey pixKey) {
        // Se a data for igual a null, retorno vazio, senão transformo para dd/mm/aaaa
        String inclusion = pixKey.inclusionDateTime() != null ? pixKey.inclusionDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
        String deactivation = pixKey.deactivationDateTime() != null ? pixKey.deactivationDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";

        return GetPixKeyResponse.builder()
                .id(pixKey.id())
                .keyType(pixKey.key().getType().getType())
                .keyValue(pixKey.key().getValue())
                .accountType(pixKey.account().getType().getType())
                .accountNumber(pixKey.account().getNumber())
                .branchNumber(pixKey.account().getBranch())
                .accountHolderName(pixKey.account().getHolderName())
                .accountHolderLastName(Objects.requireNonNullElse(pixKey.account().getHolderLastName(), ""))
                .inclusionDateTime(inclusion)
                .deactivationDateTime(deactivation)
                .build();
    }

    public static List<GetPixKeyResponse> fromDomain(List<PixKey> pixKeys) {
        return pixKeys.stream()
                .map(GetPixKeyResponse::fromDomain)
                .toList();
    }
}
