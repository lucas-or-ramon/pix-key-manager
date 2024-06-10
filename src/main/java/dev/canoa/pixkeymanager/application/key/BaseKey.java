package dev.canoa.pixkeymanager.application.key;

import dev.canoa.pixkeymanager.application.validation.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BaseKey(
        @NotNull(message = "Tipo de Chave Pix (keyType) é obrigatório")
        @ValueOfEnum(enumClass = KeyType.class, message = "Tipo de Chave Pix (keyType) deve ser 'cpf', 'cnpj', 'email', 'celular' ou 'aleatorio'")
        KeyType type,

        @Size(min = 1, max = 77, message = "Valor da Chave Pix (keyValue) deve ter entre 1 e 77 caracteres")
        @NotBlank(message = "Valor da Chave Pix (keyValue) é obrigatório")
        String value
) {
}
