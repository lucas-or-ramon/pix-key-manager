package dev.canoa.pixkeymanager.domain.ports.outbound;

import dev.canoa.pixkeymanager.domain.model.CreatePixKey;

public interface CreatePixKeyPort {
    String createPixKey(CreatePixKey pixKey);
}
