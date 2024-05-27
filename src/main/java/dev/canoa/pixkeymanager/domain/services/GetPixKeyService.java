package dev.canoa.pixkeymanager.domain.services;

import dev.canoa.pixkeymanager.domain.model.PixKey;
import dev.canoa.pixkeymanager.domain.ports.inbound.GetPixKeyUseCase;
import dev.canoa.pixkeymanager.domain.ports.outbound.GetPixKeyPort;

public class GetPixKeyService implements GetPixKeyUseCase {

    private final GetPixKeyPort getPixKeyPort;

    public GetPixKeyService(GetPixKeyPort getPixKeyPort) {
        this.getPixKeyPort = getPixKeyPort;
    }
    @Override
    public PixKey getPixKey(String key) {
        return getPixKeyPort.getPixKey(key);
    }
}
