package dev.canoa.pixkeymanager.domain.ports.inbound;

import dev.canoa.pixkeymanager.domain.model.PixKey;

public interface GetPixKeyUseCase {
    PixKey getPixKey(String key);
}
