package dev.canoa.pixkeymanager.domain.ports.outbound;

import dev.canoa.pixkeymanager.domain.model.PixKey;

public interface GetPixKeyPort {
    PixKey getPixKey(String key);
}
