package dev.canoa.pixkeymanager.domain.services;

import dev.canoa.pixkeymanager.domain.model.CreatePixKey;
import dev.canoa.pixkeymanager.domain.model.HolderType;
import dev.canoa.pixkeymanager.domain.model.Key;
import dev.canoa.pixkeymanager.domain.model.PixKey;
import dev.canoa.pixkeymanager.domain.ports.inbound.CreatePixKeyUseCase;
import dev.canoa.pixkeymanager.domain.ports.outbound.CountPixKeyPort;
import dev.canoa.pixkeymanager.domain.ports.outbound.CreatePixKeyPort;
import dev.canoa.pixkeymanager.domain.ports.outbound.ExistsPixKeyPort;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class CreatePixKeyService implements CreatePixKeyUseCase {

    private final CountPixKeyPort countPixKey;
    private final CreatePixKeyPort createPixKey;
    private final ExistsPixKeyPort existsPixKey;

    @Override
    public String execute(CreatePixKey pixKey) {
        Key key = pixKey.key();
        if (existsPixKey.exists(key.value())) {
            throw new IllegalArgumentException("Chave Pix já cadastrada");
        }

        HolderType holderType = HolderType.LEGAL_PERSON; // TODO: Não estava na especificação como obter o tipo de proprietário

        int pixKeysCount = countPixKey.count(pixKey.account(), holderType);
        if (!holderType.isValidNumberOfKeys(pixKeysCount)) {
            throw new IllegalArgumentException("Limite de chaves Pix inválido ou excedido");
        }

        PixKey create = PixKey.builder()
                .id(null)
                .key(key)
                .account(pixKey.account())
                .inclusionDateTime(LocalDateTime.now().toString())
                .build();

        return createPixKey.createPixKey(create);
    }
}
