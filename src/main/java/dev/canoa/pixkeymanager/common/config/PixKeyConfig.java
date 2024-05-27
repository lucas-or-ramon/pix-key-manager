package dev.canoa.pixkeymanager.common.config;

import dev.canoa.pixkeymanager.domain.ports.outbound.*;
import dev.canoa.pixkeymanager.domain.services.CreatePixKeyService;
import dev.canoa.pixkeymanager.domain.services.GetPixKeyService;
import dev.canoa.pixkeymanager.domain.services.UpdatePixKeyService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class PixKeyConfig {

    private final PixKeyRepository pixKeyRepository;

    @Bean
    public CreatePixKeyService createPixKeyService(IdGenerator idGenerator) {
        return new CreatePixKeyService(idGenerator, pixKeyRepository);
    }

    @Bean
    public GetPixKeyService getPixKeyService() {
        return new GetPixKeyService(pixKeyRepository);
    }

    @Bean
    public UpdatePixKeyService updatePixKeyService() {
        return new UpdatePixKeyService(pixKeyRepository);
    }
}
