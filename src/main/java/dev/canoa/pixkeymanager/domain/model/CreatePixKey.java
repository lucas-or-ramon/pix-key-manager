package dev.canoa.pixkeymanager.domain.model;

import dev.canoa.pixkeymanager.domain.model.validation.ValueOfEnum;
import jakarta.validation.constraints.*;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

@Builder
public record CreatePixKey(

        @NotNull(message = "Tipo de Chave Pix (keyType) é obrigatório")
        @ValueOfEnum(enumClass = KeyType.class, message = "Tipo de Chave Pix (keyType) deve ser 'cpf', 'cnpj', 'email', 'celular' ou 'aleatorio'")
        KeyType keyType,

        @Size(min = 1, max = 77, message = "Valor da Chave Pix (keyValue) deve ter entre 1 e 77 caracteres")
        @NotBlank(message = "Valor da Chave Pix (keyValue) é obrigatório")
        String keyValue,

        @NotNull(message = "Tipo de Conta (accountType) é obrigatório")
        @ValueOfEnum(enumClass = AccountType.class, message = "Tipo de Conta (accountType) deve ser 'corrente' ou 'poupança'")
        AccountType accountType,

        @Min(value = 1, message = "Número da Agência (branchNumber) deve ser maior que 0")
        @Max(value = 9999, message = "Número da Agência (branchNumber) deve ter no máximo 4 dígitos")
        @NotNull(message = "Número da Agência (branchNumber) é obrigatório")
        Integer branchNumber,

        @Min(value = 1, message = "Número da Conta (accountNumber) deve ser maior que 0")
        @Max(value = 99999999, message = "Número da Conta (accountNumber) deve ter no máximo 8 dígitos")
        @NotNull(message = "Número da Conta (accountNumber) é obrigatório")
        Integer accountNumber,

        @Size(min = 1, max = 30, message = "Nome do Titular da Conta (accountHolderName) deve ter entre 1 e 30 caracteres")
        @NotBlank(message = "Nome do Correntista (accountHolderName) é obrigatório")
        String accountHolderName,

        @Size(max = 45, message = "Sobrenome do Titular da Conta (accountHolderLastName) deve ter no máximo 45 caracteres")
        String accountHolderLastName,

        @NotBlank(message = "Data e hora de inclusão (inclusionDateTime) é obrigatória")
        String inclusionDateTime
) {
}