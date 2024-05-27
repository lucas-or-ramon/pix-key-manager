package dev.canoa.pixkeymanager.domain.model;

import dev.canoa.pixkeymanager.domain.model.validation.NullOrNotBlank;
import dev.canoa.pixkeymanager.domain.model.validation.ValueOfEnum;
import jakarta.validation.constraints.*;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record GetPixKey(

        @NullOrNotBlank(message = "ID da Chave Pix (id) não pode ser vazio")
        String id,

        @ValueOfEnum(enumClass = KeyType.class, message = "Tipo da Chave Pix (keyType) deve ser 'CPF', 'PHONE', 'EMAIL' ou 'RANDOM'")
        KeyType keyType,

        @Min(value = 1, message = "Número da Agência (branchNumber) deve ser maior que 0")
        @Max(value = 9999, message = "Número da Agência (branchNumber) deve ter no máximo 4 dígitos")
        Integer branchNumber,

        @Min(value = 1, message = "Número da Conta (accountNumber) deve ser maior que 0")
        @Max(value = 99999999, message = "Número da Conta (accountNumber) deve ter no máximo 8 dígitos")
        Integer accountNumber,

        @Size(min = 1, max = 30, message = "Nome do Titular da Conta (accountHolderName) deve ter entre 1 e 30 caracteres")
        @NullOrNotBlank(message = "Nome do Titular da Conta (accountHolderName) não pode ser vazio")
        String accountHolderName,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate inclusionDateTime,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate deactivationDateTime
) {

    public boolean isValid() {
        if (keyType == null && branchNumber == null && accountNumber == null && !isNotNullAndNotEmpty(accountHolderName) && inclusionDateTime == null && deactivationDateTime == null) {
            throw new IllegalArgumentException("Pelo menos um filtro deve ser informado.");
        }

        if (id != null && (keyType != null || branchNumber != null || accountNumber != null || isNotNullAndNotEmpty(accountHolderName) || inclusionDateTime != null || deactivationDateTime != null)) {
            throw new IllegalArgumentException("Se informar o ID para consulta, nenhum outro filtro pode ser aceito.");
        }

        if (inclusionDateTime != null && deactivationDateTime != null) {
            throw new IllegalArgumentException("Não permitir a combinação de filtros Data de inclusão da chave e Data da inativação da chave. Somente um ou outro.");
        }

        if ((branchNumber != null && accountNumber == null) || (branchNumber == null && accountNumber != null)) {
            throw new IllegalArgumentException("Numero da agência e número da conta devem ser informados juntos.");
        }

        return true;
    }

    private boolean isNotNullAndNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }
}
