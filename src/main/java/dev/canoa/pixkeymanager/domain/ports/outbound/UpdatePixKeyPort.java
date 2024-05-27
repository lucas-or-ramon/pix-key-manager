package dev.canoa.pixkeymanager.domain.ports.outbound;

import dev.canoa.pixkeymanager.domain.model.PixKey;

public interface UpdatePixKeyPort {
    PixKey updatePixKey(String key, PixKey pixKey);
}
