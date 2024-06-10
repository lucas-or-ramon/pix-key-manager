package dev.canoa.pixkeymanager.application.services;

import dev.canoa.pixkeymanager.application.GetPixKey;
import dev.canoa.pixkeymanager.application.PixKey;
import dev.canoa.pixkeymanager.application.exception.NotFoundPixKeyException;
import dev.canoa.pixkeymanager.application.GetPixKeyUseCase;
import dev.canoa.pixkeymanager.application.PixKeyRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetPixKeyService implements GetPixKeyUseCase {

    private final PixKeyRepository pixKeyRepository;

    @Override
    public List<PixKey> execute(GetPixKey params) {
        if (!params.isValid()) {
            throw new IllegalArgumentException("Parâmetros inválidos");
        }

        List<PixKey> pixKeys;
        if (params.id() != null) {
            pixKeys = List.of(pixKeyRepository.findById(params.id()));
        } else {
            pixKeys = pixKeyRepository.findWith(params);
        }

        if (pixKeys.isEmpty()) {
            throw new NotFoundPixKeyException("Chave Pix não encontrada");
        }

        return pixKeys;
    }
}
