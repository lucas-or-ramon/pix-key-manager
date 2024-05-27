package dev.canoa.pixkeymanager.domain.ports.outbound;

import dev.canoa.pixkeymanager.domain.model.PixKey;

public interface CreatePixKeyPort {
    void createPixKey(PixKey pixKey);
}
