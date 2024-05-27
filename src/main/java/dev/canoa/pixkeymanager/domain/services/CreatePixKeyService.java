package dev.canoa.pixkeymanager.domain.services;

import dev.canoa.pixkeymanager.domain.model.PixKey;
import dev.canoa.pixkeymanager.domain.ports.inbound.CreatePixKeyUseCase;
import dev.canoa.pixkeymanager.domain.ports.outbound.CreatePixKeyPort;

public class CreatePixKeyService implements CreatePixKeyUseCase {

    private final CreatePixKeyPort createPixKeyPort;

    public CreatePixKeyService(CreatePixKeyPort createPixKeyPort) {
        this.createPixKeyPort = createPixKeyPort;
    }

    @Override
    public void createPixKey(PixKey pixKey) {
        createPixKeyPort.createPixKey(pixKey);
    }
}
