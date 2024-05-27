package dev.canoa.pixkeymanager.infrastructure.databases.postgresql;

import dev.canoa.pixkeymanager.domain.model.PixKey;
import dev.canoa.pixkeymanager.domain.model.UpdatePixKey;
import dev.canoa.pixkeymanager.domain.ports.outbound.UpdatePixKeyPort;
import org.springframework.stereotype.Component;

@Component
public class UpdatePixKeyAdapter implements UpdatePixKeyPort {

    @Override
    public PixKey updatePixKey(String key, UpdatePixKey pixKey) {
        return PixKey.builder().build();
    }
}
