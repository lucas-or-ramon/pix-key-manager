package dev.canoa.pixkeymanager.infrastructure.databases.postgresql;

import dev.canoa.pixkeymanager.domain.model.CreatePixKey;
import dev.canoa.pixkeymanager.domain.ports.outbound.CreatePixKeyPort;
import org.springframework.stereotype.Component;

@Component
public class CreatePixKeyAdapter implements CreatePixKeyPort {

    @Override
    public String createPixKey(CreatePixKey pixKey) {
        // TODO Auto-generated method stub
        return pixKey.keyType().getType();
    }
}
