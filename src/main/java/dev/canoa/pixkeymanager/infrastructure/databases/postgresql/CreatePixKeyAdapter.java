package dev.canoa.pixkeymanager.infrastructure.databases.postgresql;

import dev.canoa.pixkeymanager.domain.model.PixKey;
import dev.canoa.pixkeymanager.domain.ports.outbound.CreatePixKeyPort;
import org.springframework.stereotype.Component;

@Component
public class CreatePixKeyAdapter implements CreatePixKeyPort {

    @Override
    public String createPixKey(PixKey pixKey) {
        // TODO Auto-generated method stub
        return pixKey.keyType().getType();
    }
}
