package dev.canoa.pixkeymanager.domain.ports.inbound;

import dev.canoa.pixkeymanager.domain.model.CreatePixKey;
import jakarta.validation.Valid;

public interface CreatePixKeyUseCase {
    String execute(@Valid CreatePixKey pixKey);
}
