package dev.canoa.pixkeymanager.domain.services;

import dev.canoa.pixkeymanager.domain.model.Account;
import dev.canoa.pixkeymanager.domain.model.PixKey;
import dev.canoa.pixkeymanager.domain.ports.inbound.UpdatePixKeyUseCase;
import dev.canoa.pixkeymanager.domain.ports.outbound.GetPixKeyPort;
import dev.canoa.pixkeymanager.domain.ports.outbound.UpdatePixKeyPort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdatePixKeyService implements UpdatePixKeyUseCase {

    private final GetPixKeyPort getPixKeyPort;
    private final UpdatePixKeyPort updatePixKeyPort;

    @Override
    public PixKey execute(String id, Account account) {
        PixKey existingPixKey = getPixKeyPort.getPixKey(id);
        if (existingPixKey == null) {
            throw new IllegalArgumentException("Charge Pix não encontrada");
        }

        PixKey update = PixKey.builder()
                .id(existingPixKey.id())
                .key(existingPixKey.key())
                .inclusionDateTime(existingPixKey.inclusionDateTime())
                .account(account)
                .build();

        return updatePixKeyPort.updatePixKey(id, update);
    }
}
