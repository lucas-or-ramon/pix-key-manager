package dev.canoa.pixkeymanager.common.config;

import dev.canoa.pixkeymanager.domain.ports.outbound.CreatePixKeyPort;
import dev.canoa.pixkeymanager.domain.ports.outbound.GetPixKeyPort;
import dev.canoa.pixkeymanager.domain.ports.outbound.UpdatePixKeyPort;
import dev.canoa.pixkeymanager.domain.services.CreatePixKeyService;
import dev.canoa.pixkeymanager.domain.services.GetPixKeyService;
import dev.canoa.pixkeymanager.domain.services.UpdatePixKeyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PixKeyConfig {

    @Bean
    public CreatePixKeyService createPixKeyService(CreatePixKeyPort createPixKeyPort) {
        return new CreatePixKeyService(createPixKeyPort);
    }

    @Bean
    public GetPixKeyService getPixKeyService(GetPixKeyPort getPixKeyPort) {
        return new GetPixKeyService(getPixKeyPort);
    }

    @Bean
    public UpdatePixKeyService updatePixKeyService(UpdatePixKeyPort updatePixKeyPort) {
        return new UpdatePixKeyService(updatePixKeyPort);
    }
}
