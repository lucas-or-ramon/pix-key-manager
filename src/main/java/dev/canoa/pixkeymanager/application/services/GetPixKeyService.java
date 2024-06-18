package dev.canoa.pixkeymanager.application.services;

import dev.canoa.pixkeymanager.application.GetPixKey;
import dev.canoa.pixkeymanager.application.PixKey;
import dev.canoa.pixkeymanager.application.GetPixKeyUseCase;
import dev.canoa.pixkeymanager.application.PixKeyRepository;
import io.jbock.util.Either;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class GetPixKeyService implements GetPixKeyUseCase {

    private final PixKeyRepository pixKeyRepository;

    @Override
    public Either<List<PixKey>, Error> execute(GetPixKey params) {
        if (!params.isValid()) {
            throw new IllegalArgumentException("Parâmetros inválidos");
        }

        List<PixKey> pixKeys = getPixKeys(params);
        if (pixKeys.isEmpty()) {
            return Either.right(new Error("Nenhuma chave Pix encontrada"));
        }

        return Either.left(pixKeys);
    }

    private List<PixKey> getPixKeys(GetPixKey params) {
        if (params.id() != null) {
            return getPixKeyAsList(params.id());
        } else {
            return getPixKeysWitParams(params);
        }
    }

    private List<PixKey> getPixKeyAsList(String id) {
        Optional<PixKey> optionalPixKey = pixKeyRepository.findById(id);
        return optionalPixKey.map(List::of).orElse(Collections.emptyList());
    }

    private List<PixKey> getPixKeysWitParams(GetPixKey params) {
        return pixKeyRepository.findWith(params);
    }
}
