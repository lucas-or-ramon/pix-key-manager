package dev.canoa.pixkeymanager.infrastructure.databases.postgresql;

import dev.canoa.pixkeymanager.domain.ports.outbound.ExistsPixKeyPort;
import org.springframework.stereotype.Component;

@Component
public class ExistsPixKeyAdapter implements ExistsPixKeyPort {

    @Override
    public boolean exists(String key) {
        return false;
    }
}
