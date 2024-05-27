package dev.canoa.pixkeymanager.domain.ports.inbound;

import dev.canoa.pixkeymanager.domain.model.PixKey;
import dev.canoa.pixkeymanager.domain.model.UpdatePixKey;

public interface UpdatePixKeyUseCase {
    PixKey execute(String id, UpdatePixKey pixKey);
}
