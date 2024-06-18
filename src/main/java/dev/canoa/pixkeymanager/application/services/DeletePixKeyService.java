package dev.canoa.pixkeymanager.application.services;

import dev.canoa.pixkeymanager.application.PixKey;
import dev.canoa.pixkeymanager.application.DeletePixKeyUseCase;
import dev.canoa.pixkeymanager.application.PixKeyRepository;
import io.jbock.util.Either;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
public class DeletePixKeyService implements DeletePixKeyUseCase {

    private final PixKeyRepository pixKeyRepository;
    @Override
    public Either<PixKey, Error> execute(String id) {
        Optional<PixKey> optionalPixKey = pixKeyRepository.findById(id);
        if (optionalPixKey.isEmpty()) {
            return Either.right(new Error("Chave Pix não encontrada"));
        }
        PixKey pixKey = optionalPixKey.get();

        if (pixKey.deactivationDateTime() != null) {
            return Either.right(new Error("Chave Pix já desativada"));
        }

        PixKey updatedPixKey = PixKey.builder()
                .id(pixKey.id())
                .key(pixKey.key())
                .account(pixKey.account())
                .inclusionDateTime(pixKey.inclusionDateTime())
                .deactivationDateTime(LocalDateTime.now())
                .build();

        return Either.left(pixKeyRepository.update(updatedPixKey));
    }
}
