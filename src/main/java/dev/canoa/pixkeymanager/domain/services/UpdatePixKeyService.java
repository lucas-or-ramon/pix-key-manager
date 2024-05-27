package dev.canoa.pixkeymanager.domain.services;

import dev.canoa.pixkeymanager.domain.model.Account;
import dev.canoa.pixkeymanager.domain.model.PixKey;
import dev.canoa.pixkeymanager.domain.ports.inbound.UpdatePixKeyUseCase;
import dev.canoa.pixkeymanager.domain.ports.outbound.PixKeyRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdatePixKeyService implements UpdatePixKeyUseCase {

    private final PixKeyRepository pixKeyRepository;

    @Override
    public PixKey execute(String id, Account account) {
        PixKey existingPixKey = this.getExistingPixKey(id);

        PixKey pixKey = PixKey.builder()
                .id(existingPixKey.id())
                .key(existingPixKey.key())
                .inclusionDateTime(existingPixKey.inclusionDateTime())
                .account(account)
                .build();

        return pixKeyRepository.update(pixKey);
    }

    private PixKey getExistingPixKey(String id) {
        PixKey pixKey = pixKeyRepository.findById(id);
        if (pixKey == null) {
            throw new IllegalArgumentException("Chave Pix não encontrada");
        }
        return pixKey;
    }
}
