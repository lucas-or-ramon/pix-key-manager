package dev.canoa.pixkeymanager.application.rest.response;

import lombok.Builder;

@Builder
public record CreatePixKeyResponse(String keyType, String keyValue, String accountType, String branchNumber,
                                   String accountNumber, String accountHolderName, String accountHolderLastName) {
}
