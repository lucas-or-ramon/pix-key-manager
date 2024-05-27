package dev.canoa.pixkeymanager.domain.ports.inbound;

import dev.canoa.pixkeymanager.domain.model.CreatePixKey;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CreatePixKeyUseCase {
    String execute(@Valid CreatePixKey pixKey);
}
