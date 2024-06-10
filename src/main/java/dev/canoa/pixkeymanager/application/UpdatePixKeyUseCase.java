package dev.canoa.pixkeymanager.application;

import dev.canoa.pixkeymanager.application.account.Account;
import dev.canoa.pixkeymanager.application.PixKey;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UpdatePixKeyUseCase {
    PixKey execute(@NotBlank String id, @Valid Account account);
}
