package dev.canoa.pixkeymanager.domain.ports.outbound;

import dev.canoa.pixkeymanager.domain.model.PixKey;

public interface UpdatePixKeyPort {
    void updatePixKey(String key, PixKey pixKey);
}
