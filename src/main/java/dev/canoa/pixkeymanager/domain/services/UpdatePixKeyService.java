package dev.canoa.pixkeymanager.domain.services;

import dev.canoa.pixkeymanager.domain.model.PixKey;
import dev.canoa.pixkeymanager.domain.model.UpdatePixKey;
import dev.canoa.pixkeymanager.domain.ports.inbound.UpdatePixKeyUseCase;
import dev.canoa.pixkeymanager.domain.ports.outbound.UpdatePixKeyPort;
import jakarta.validation.Valid;

public class UpdatePixKeyService implements UpdatePixKeyUseCase {

    private final UpdatePixKeyPort updatePixKeyPort;

    public UpdatePixKeyService(UpdatePixKeyPort updatePixKeyPort) {
        this.updatePixKeyPort = updatePixKeyPort;
    }

    @Override
    public PixKey execute(String id, @Valid UpdatePixKey pixKey) {
        return updatePixKeyPort.updatePixKey(id, pixKey);
    }
}
