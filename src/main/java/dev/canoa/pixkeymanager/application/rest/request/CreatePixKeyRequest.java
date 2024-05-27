package dev.canoa.pixkeymanager.application.rest.request;

import dev.canoa.pixkeymanager.domain.model.AccountType;
import dev.canoa.pixkeymanager.domain.model.KeyType;
import dev.canoa.pixkeymanager.domain.model.PixKey;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CreatePixKeyRequest(
        @Size(min = 1, max = 9, message = "Tipo de Chave Pix (keyType) deve ter entre 1 e 9 caracteres")
        @NotBlank(message = "Tipo de Chave Pix (keyType) é obrigatório")
        String keyType,

        @Size(min = 1, max = 77, message = "Valor da Chave Pix (keyValue) deve ter entre 1 e 77 caracteres")
        @NotBlank(message = "Valor da Chave Pix (keyValue) é obrigatório")
        String keyValue,

        @Size(min = 1, max = 10, message = "Tipo de Conta (accountType) deve ter entre 1 e 10 caracteres")
        @NotBlank(message = "Tipo de Conta (accountType) é obrigatório")
        String accountType,

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
        String accountHolderLastName
) {
    public PixKey toModel() {
        return PixKey.builder()
                .keyType(KeyType.from(keyType))
                .keyValue(keyValue)
                .accountType(AccountType.from(accountType))
                .branchNumber(branchNumber)
                .accountNumber(accountNumber)
                .accountHolderName(accountHolderName)
                .accountHolderLastName(accountHolderLastName)
                .inclusionDateTime(LocalDateTime.now().toString())
                .build();
    }
}
