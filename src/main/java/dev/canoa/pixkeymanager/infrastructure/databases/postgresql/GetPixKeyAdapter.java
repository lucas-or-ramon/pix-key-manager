package dev.canoa.pixkeymanager.infrastructure.databases.postgresql;

import dev.canoa.pixkeymanager.domain.model.PixKey;
import dev.canoa.pixkeymanager.domain.ports.outbound.GetPixKeyPort;
import org.springframework.stereotype.Component;

@Component
public class GetPixKeyAdapter implements GetPixKeyPort {

    @Override
    public PixKey getPixKey(String key) {
        // TODO Auto-generated method stub
        return PixKey.builder().build();
    }
}
