package dev.canoa.pixkeymanager.domain.services;

import dev.canoa.pixkeymanager.domain.model.GetPixKey;
import dev.canoa.pixkeymanager.domain.model.PixKey;
import dev.canoa.pixkeymanager.domain.model.exception.NotFoundPixKeyException;
import dev.canoa.pixkeymanager.domain.ports.inbound.GetPixKeyUseCase;
import dev.canoa.pixkeymanager.domain.ports.outbound.PixKeyRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetPixKeyService implements GetPixKeyUseCase {

    private final PixKeyRepository pixKeyRepository;

    @Override
    public List<PixKey> getPixKeys(GetPixKey params) {
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
