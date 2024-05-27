package dev.canoa.pixkeymanager.domain.ports.outbound;

import dev.canoa.pixkeymanager.domain.model.PixKey;

public interface CreatePixKeyPort {
    String createPixKey(PixKey pixKey);
}
