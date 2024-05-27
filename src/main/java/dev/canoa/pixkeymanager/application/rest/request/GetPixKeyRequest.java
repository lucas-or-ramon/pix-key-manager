package dev.canoa.pixkeymanager.application.rest.request;

import dev.canoa.pixkeymanager.domain.model.GetPixKey;
import dev.canoa.pixkeymanager.domain.model.KeyType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        // Trasform deactivationDateTime and inclusionDateTime from format "dd/MM/yyyy" to LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inclusionDateTime = this.inclusionDateTime != null ? LocalDate.parse(this.inclusionDateTime, formatter) : null;
        LocalDate deactivationDateTime = this.deactivationDateTime != null ? LocalDate.parse(this.deactivationDateTime, formatter) : null;
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
