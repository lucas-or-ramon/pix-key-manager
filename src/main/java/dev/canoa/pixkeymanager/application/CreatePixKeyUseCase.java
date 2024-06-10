package dev.canoa.pixkeymanager.application;

import dev.canoa.pixkeymanager.application.PixKey;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CreatePixKeyUseCase {
    String execute(@Valid PixKey pixKey);
}
