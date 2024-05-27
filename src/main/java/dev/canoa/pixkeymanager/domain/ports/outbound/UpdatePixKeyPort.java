package dev.canoa.pixkeymanager.domain.ports.outbound;

import dev.canoa.pixkeymanager.domain.model.PixKey;
import dev.canoa.pixkeymanager.domain.model.UpdatePixKey;

public interface UpdatePixKeyPort {
    PixKey updatePixKey(String key, UpdatePixKey pixKey);
}
