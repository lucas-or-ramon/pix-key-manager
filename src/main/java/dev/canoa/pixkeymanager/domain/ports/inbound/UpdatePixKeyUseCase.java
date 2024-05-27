package dev.canoa.pixkeymanager.domain.ports.inbound;

import dev.canoa.pixkeymanager.domain.model.Account;
import dev.canoa.pixkeymanager.domain.model.PixKey;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UpdatePixKeyUseCase {
    PixKey execute(@NotBlank String id, @Valid Account account);
}
