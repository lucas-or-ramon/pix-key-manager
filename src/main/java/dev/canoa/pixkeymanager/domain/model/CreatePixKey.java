package dev.canoa.pixkeymanager.domain.model;

import dev.canoa.pixkeymanager.domain.model.validation.PixKeyValue;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreatePixKey(

        @NotNull
        @PixKeyValue
        Key key,

        @NotNull
        Account account
) {
}