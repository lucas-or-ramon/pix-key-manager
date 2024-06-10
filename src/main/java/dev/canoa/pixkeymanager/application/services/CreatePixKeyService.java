package dev.canoa.pixkeymanager.application.services;

import dev.canoa.pixkeymanager.application.CreatePixKeyUseCase;
import dev.canoa.pixkeymanager.application.PixKey;
import dev.canoa.pixkeymanager.application.PixKeyRepository;
import dev.canoa.pixkeymanager.application.account.Account;
import dev.canoa.pixkeymanager.application.account.AccountRepository;
import dev.canoa.pixkeymanager.application.account.HolderType;
import dev.canoa.pixkeymanager.application.key.Key;
import dev.canoa.pixkeymanager.application.key.KeyRepository;
import dev.canoa.pixkeymanager.application.uuid.IdGenerator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class CreatePixKeyService implements CreatePixKeyUseCase {

    private final IdGenerator idGenerator;
    private final KeyRepository keyRepository;
    private final PixKeyRepository pixKeyRepository;
    private final AccountRepository accountRepository;

    public String execute(@Valid PixKey pixKey) {
        verifyKeyNotExists(pixKey.key().getValue());

        Key key = keyRepository.create(pixKey.key());
        Account account = findOrCreateAccount(pixKey.account());

        validatePixKeyLimit(account);

        String id = generateUniquePixKeyId();
        PixKey newPixKey = createNewPixKey(id, key, account);

        return pixKeyRepository.create(newPixKey).id();
    }

    private void verifyKeyNotExists(String keyValue) {
        if (keyRepository.findByValue(keyValue).isPresent()) {
            throw new IllegalArgumentException("Chave Pix já cadastrada");
        }
    }

    private Account findOrCreateAccount(Account accountData) {
        return accountRepository.findByBranchAndAccountNumber(accountData.getBranch(), accountData.getNumber())
                .orElseGet(() -> accountRepository.create(accountData));
    }

    private void validatePixKeyLimit(Account account) {
        HolderType holderType = HolderType.NATURAL_PERSON;
        long total = pixKeyRepository.count(account.getId());
        if (!holderType.isValidNumberOfKeys(total)) {
            throw new IllegalArgumentException("Limite de chaves Pix inválido ou excedido");
        }
    }

    private String generateUniquePixKeyId() {
        String id = idGenerator.generate();
        if (pixKeyRepository.findById(id).isPresent()) {
            throw new IllegalStateException("Id já existe");
        }
        return id;
    }

    private PixKey createNewPixKey(String id, Key key, Account account) {
        return PixKey.builder()
                .id(id)
                .key(key)
                .account(account)
                .inclusionDateTime(LocalDateTime.now())
                .build();
    }
}
