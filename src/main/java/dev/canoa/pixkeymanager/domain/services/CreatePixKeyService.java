package dev.canoa.pixkeymanager.domain.services;

import dev.canoa.pixkeymanager.domain.model.CreatePixKey;
import dev.canoa.pixkeymanager.domain.model.HolderType;
import dev.canoa.pixkeymanager.domain.ports.inbound.CreatePixKeyUseCase;
import dev.canoa.pixkeymanager.domain.ports.outbound.CountPixKeyPort;
import dev.canoa.pixkeymanager.domain.ports.outbound.CreatePixKeyPort;
import dev.canoa.pixkeymanager.domain.ports.outbound.ExistsPixKeyPort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@Validated
public class CreatePixKeyService implements CreatePixKeyUseCase {

    private final CountPixKeyPort countPixKey;
    private final CreatePixKeyPort createPixKey;
    private final ExistsPixKeyPort existsPixKey;

    @Override
    public String execute(@Valid CreatePixKey pixKey) {
        if (existsPixKey.exists(pixKey.keyValue())) {
            throw new IllegalArgumentException("Chave Pix já cadastrada");
        }

        HolderType holderType = HolderType.LEGAL_PERSON; // TODO: Não estava na especificação como obter o tipo de proprietário
        int pixKeysCount = countPixKey.count(pixKey.accountNumber(), pixKey.branchNumber(), holderType);
        if (!holderType.isValidNumberOfKeys(pixKeysCount)) {
            throw new IllegalArgumentException("Limite de chaves Pix inválido ou excedido");
        }

        return createPixKey.createPixKey(pixKey);
    }
}
