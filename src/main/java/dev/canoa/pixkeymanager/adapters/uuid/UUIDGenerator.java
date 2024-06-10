package dev.canoa.pixkeymanager.adapters.uuid;

import com.fasterxml.uuid.Generators;
import dev.canoa.pixkeymanager.application.uuid.IdGenerator;
import org.springframework.stereotype.Component;

@Component
public class UUIDGenerator implements IdGenerator {

    @Override
    public String generate() {
        return Generators.randomBasedGenerator().generate().toString();
    }
}
