package dev.canoa.pixkeymanager.infrastructure.uuid;

import com.fasterxml.uuid.Generators;
import dev.canoa.pixkeymanager.domain.ports.outbound.IdGenerator;
import org.springframework.stereotype.Component;

@Component
public class UUIDGenerator implements IdGenerator {

    @Override
    public String generate() {
        return Generators.randomBasedGenerator().generate().toString();
    }
}
