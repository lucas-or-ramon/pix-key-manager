package dev.canoa.pixkeymanager.domain.services;

import dev.canoa.pixkeymanager.domain.model.PixKey;
import dev.canoa.pixkeymanager.domain.ports.inbound.UpdatePixKeyUseCase;
import dev.canoa.pixkeymanager.domain.ports.outbound.UpdatePixKeyPort;

public class UpdatePixKeyService implements UpdatePixKeyUseCase {

    private final UpdatePixKeyPort updatePixKeyPort;

    public UpdatePixKeyService(UpdatePixKeyPort updatePixKeyPort) {
        this.updatePixKeyPort = updatePixKeyPort;
    }

    @Override
    public void updatePixKey(String key, PixKey pixKey) {
        updatePixKeyPort.updatePixKey(key, pixKey);
    }
}
