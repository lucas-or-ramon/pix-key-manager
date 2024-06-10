package dev.canoa.pixkeymanager.application;

import dev.canoa.pixkeymanager.application.account.Account;
import dev.canoa.pixkeymanager.application.key.Key;
import dev.canoa.pixkeymanager.application.validation.NullOrNotBlank;
import dev.canoa.pixkeymanager.application.key.validation.PixKeyValue;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
public record PixKey(
        @NullOrNotBlank(message = "ID da Chave Pix (id) não pode ser vazio") String id,

        @Valid @NotNull @PixKeyValue Key key,

        @Valid @NotNull Account account,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inclusionDateTime,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime deactivationDateTime
) {
}
