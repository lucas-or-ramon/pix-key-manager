package dev.canoa.pixkeymanager.domain.ports.inbound;

import dev.canoa.pixkeymanager.domain.model.PixKey;

public interface UpdatePixKeyUseCase {
    void updatePixKey(String key, PixKey pixKey);
}
