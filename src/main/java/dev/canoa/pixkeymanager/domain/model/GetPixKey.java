package dev.canoa.pixkeymanager.domain.model;

import dev.canoa.pixkeymanager.domain.model.validation.NullOrNotBlank;
import dev.canoa.pixkeymanager.domain.model.validation.ValueOfEnum;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

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

        @NullOrNotBlank(message = "Data de inclusão da chave (inclusionDateTime) não pode ser vazia")
        String inclusionDateTime,

        @NullOrNotBlank(message = "Data de inativação da chave (deactivationDateTime) não pode ser vazia")
        String deactivationDateTime
) {

    public boolean isValid() {
        if (id != null && (keyType != null || branchNumber != null || accountNumber != null || isNullOrEmpty(accountHolderName) || isNullOrEmpty(inclusionDateTime) || isNullOrEmpty(deactivationDateTime))) {
            throw new IllegalArgumentException("Se informar o ID para consulta, nenhum outro filtro pode ser aceito.");
        }

        if (isNullOrEmpty(inclusionDateTime) && isNullOrEmpty(deactivationDateTime)) {
            throw new IllegalArgumentException("Não permitir a combinação de filtros Data de inclusão da chave e Data da inativação da chave. Somente um ou outro.");
        }

        if ((branchNumber != null && accountNumber == null) || (branchNumber == null && accountNumber != null)) {
            throw new IllegalArgumentException("Numero da agência e número da conta devem ser informados juntos.");
        }

        return true;
    }

    private boolean isNullOrEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }
}
