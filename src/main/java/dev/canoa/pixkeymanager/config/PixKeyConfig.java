package dev.canoa.pixkeymanager.config;

import dev.canoa.pixkeymanager.application.PixKeyRepository;
import dev.canoa.pixkeymanager.application.services.CreatePixKeyService;
import dev.canoa.pixkeymanager.application.services.DeletePixKeyService;
import dev.canoa.pixkeymanager.application.services.GetPixKeyService;
import dev.canoa.pixkeymanager.application.services.UpdatePixKeyService;
import dev.canoa.pixkeymanager.application.uuid.IdGenerator;
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

    @Bean
    public DeletePixKeyService deletePixKeyService() {
        return new DeletePixKeyService(pixKeyRepository);
    }
}
