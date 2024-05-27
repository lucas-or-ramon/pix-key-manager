package dev.canoa.pixkeymanager.domain.model;

import dev.canoa.pixkeymanager.domain.model.validation.ValueOfEnum;
import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record Account(
        @NotNull(message = "Tipo de Conta (accountType) é obrigatório")
        @ValueOfEnum(enumClass = AccountType.class, message = "Tipo de Conta (accountType) deve ser 'corrente' ou 'poupança'")
        AccountType type,

        @Min(value = 1, message = "Número da Agência (branchNumber) deve ser maior que 0")
        @Max(value = 9999, message = "Número da Agência (branchNumber) deve ter no máximo 4 dígitos")
        @NotNull(message = "Número da Agência (branchNumber) é obrigatório")
        Integer branch,

        @Min(value = 1, message = "Número da Conta (accountNumber) deve ser maior que 0")
        @Max(value = 99999999, message = "Número da Conta (accountNumber) deve ter no máximo 8 dígitos")
        @NotNull(message = "Número da Conta (accountNumber) é obrigatório")
        Integer number,

        @Size(min = 1, max = 30, message = "Nome do Titular da Conta (accountHolderName) deve ter entre 1 e 30 caracteres")
        @NotBlank(message = "Nome do Correntista (accountHolderName) é obrigatório")
        String holderName,

        @Size(max = 45, message = "Sobrenome do Titular da Conta (accountHolderLastName) deve ter no máximo 45 caracteres")
        String holderLastName
) {
}
