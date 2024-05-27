package dev.canoa.pixkeymanager.domain.model;

import dev.canoa.pixkeymanager.domain.model.validation.PixKeyValue;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreatePixKey(

        @Valid
        @NotNull
        @PixKeyValue
        Key key,

        @Valid
        @NotNull
        Account account
) {
}