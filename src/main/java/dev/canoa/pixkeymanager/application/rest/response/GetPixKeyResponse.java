package dev.canoa.pixkeymanager.application.rest.response;

import lombok.Builder;

@Builder
public record GetPixKeyResponse(String id, String keyType, String keyValue, String accountType, String branchNumber,
                                String accountNumber, String accountHolderName, String accountHolderLastName,
                                String inclusionDateTime, String deactivationDateTime) {
}
