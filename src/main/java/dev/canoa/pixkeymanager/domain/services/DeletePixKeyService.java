package dev.canoa.pixkeymanager.domain.services;

import dev.canoa.pixkeymanager.domain.model.PixKey;
import dev.canoa.pixkeymanager.domain.ports.inbound.DeletePixKeyUseCase;
import dev.canoa.pixkeymanager.domain.ports.outbound.PixKeyRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class DeletePixKeyService implements DeletePixKeyUseCase {

    private final PixKeyRepository pixKeyRepository;
    @Override
    public PixKey delete(String id) {
        PixKey pixKey = pixKeyRepository.findById(id);
        if (pixKey == null) {
            throw new IllegalArgumentException("Chave Pix não encontrada");
        }

        if (pixKey.deactivationDateTime() != null) {
            throw new IllegalArgumentException("Chave Pix já desativada");
        }

        PixKey updatedPixKey = PixKey.builder()
                .id(pixKey.id())
                .key(pixKey.key())
                .account(pixKey.account())
                .inclusionDateTime(pixKey.inclusionDateTime())
                .deactivationDateTime(LocalDateTime.now())
                .build();

        return pixKeyRepository.update(updatedPixKey);
    }
}
