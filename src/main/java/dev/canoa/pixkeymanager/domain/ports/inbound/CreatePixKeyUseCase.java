package dev.canoa.pixkeymanager.domain.ports.inbound;

import dev.canoa.pixkeymanager.domain.model.PixKey;

public interface CreatePixKeyUseCase {
    void createPixKey(PixKey pixKey);
}
