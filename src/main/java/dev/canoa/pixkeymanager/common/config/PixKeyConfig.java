package dev.canoa.pixkeymanager.common.config;

import dev.canoa.pixkeymanager.domain.ports.outbound.*;
import dev.canoa.pixkeymanager.domain.services.CreatePixKeyService;
import dev.canoa.pixkeymanager.domain.services.GetPixKeyService;
import dev.canoa.pixkeymanager.domain.services.UpdatePixKeyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PixKeyConfig {

    @Bean
    public CreatePixKeyService createPixKeyService(CountPixKeyPort countPixKeyPort, CreatePixKeyPort createPixKeyPort, ExistsPixKeyPort existsPixKeyPort) {
        return new CreatePixKeyService(countPixKeyPort, createPixKeyPort, existsPixKeyPort);
    }

    @Bean
    public GetPixKeyService getPixKeyService(GetPixKeyPort getPixKeyPort) {
        return new GetPixKeyService(getPixKeyPort);
    }

    @Bean
    public UpdatePixKeyService updatePixKeyService(GetPixKeyPort getPixKeyPort, UpdatePixKeyPort updatePixKeyPort) {
        return new UpdatePixKeyService(getPixKeyPort, updatePixKeyPort);
    }
}
