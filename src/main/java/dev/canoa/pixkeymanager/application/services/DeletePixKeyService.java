package dev.canoa.pixkeymanager.application.services;

import dev.canoa.pixkeymanager.application.PixKey;
import dev.canoa.pixkeymanager.application.DeletePixKeyUseCase;
import dev.canoa.pixkeymanager.application.PixKeyRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
public class DeletePixKeyService implements DeletePixKeyUseCase {

    private final PixKeyRepository pixKeyRepository;
    @Override
    public PixKey execute(String id) {
        Optional<PixKey> optionalPixKey = pixKeyRepository.findById(id);
        if (optionalPixKey.isEmpty()) {
            throw new IllegalArgumentException("Chave Pix não encontrada");
        }
        PixKey pixKey = optionalPixKey.get();

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
