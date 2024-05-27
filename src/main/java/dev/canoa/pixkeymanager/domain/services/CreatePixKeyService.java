package dev.canoa.pixkeymanager.domain.services;

import dev.canoa.pixkeymanager.domain.model.*;
import dev.canoa.pixkeymanager.domain.ports.inbound.CreatePixKeyUseCase;
import dev.canoa.pixkeymanager.domain.ports.outbound.IdGenerator;
import dev.canoa.pixkeymanager.domain.ports.outbound.PixKeyRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class CreatePixKeyService implements CreatePixKeyUseCase {

    private final IdGenerator idGenerator;
    private final PixKeyRepository pixKeyRepository;

    @Override
    public String execute(CreatePixKey pixKey) {
        PixKey newPixKey = PixKey.builder()
                .id(this.getId())
                .key(this.getKey(pixKey))
                .account(this.getAccount(pixKey.account()))
                .inclusionDateTime(LocalDateTime.now().toString())
                .build();

        return pixKeyRepository.create(newPixKey);
    }

    private String getId() {
        String id = idGenerator.generate();
        boolean existsId = pixKeyRepository.existsId(id);
        if (existsId) {
            throw new IllegalStateException("Id já existe");
        }
        return id;
    }

    private Key getKey(CreatePixKey pixKey) {
        if (pixKeyRepository.existsKeyValue(pixKey.key().value())) {
            throw new IllegalArgumentException("Chave Pix já cadastrada");
        }
        if (pixKeyRepository.existsKeyType(pixKey.key().type(), pixKey.account().branch(), pixKey.account().number())) {
            throw new IllegalArgumentException("Tipo de chave Pix já cadastrada");
        }
        return pixKey.key();
    }

    private Account getAccount(Account account) {
        HolderType holderType = HolderType.LEGAL_PERSON; // TODO: Não estava na especificação como obter o tipo de proprietário
        long total = pixKeyRepository.count(holderType, account.branch(), account.number());
        if (!holderType.isValidNumberOfKeys(total)) {
            throw new IllegalArgumentException("Limite de chaves Pix inválido ou excedido");
        }
        return account;
    }
}
