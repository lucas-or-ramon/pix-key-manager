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
import io.jbock.util.Either;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class CreatePixKeyService implements CreatePixKeyUseCase {

    private final IdGenerator idGenerator;
    private final KeyRepository keyRepository;
    private final PixKeyRepository pixKeyRepository;
    private final AccountRepository accountRepository;

    public Either<String, Error> execute(@Valid PixKey pixKey) {
        if (isKeyExisting(pixKey.key().getValue())) {
            return Either.right(new Error("Chave já cadastrada"));
        }

        Key key = createKey(pixKey.key());
        Account account = getOrCreateAccount(pixKey.account());

        if (!isPixKeyLimitValid(account)) {
            return Either.right(new Error("Limite de chaves atingido"));
        }

        Either<String, Error> uniquePixKeyId = generateUniquePixKeyId();
        if (uniquePixKeyId.isRight() || uniquePixKeyId.getLeft().isEmpty()) {
            return uniquePixKeyId;
        }

        PixKey newPixKey = createNewPixKey(uniquePixKeyId.getLeft().get(), key, account);
        return Either.left(pixKeyRepository.create(newPixKey).id());
    }

    private boolean isKeyExisting(String keyValue) {
        return keyRepository.findByValue(keyValue).isPresent();
    }

    private Key createKey(Key key) {
        return keyRepository.create(key);
    }

    private Account getOrCreateAccount(Account accountData) {
        return accountRepository.findByBranchAndAccountNumber(accountData.getBranch(), accountData.getNumber())
                .orElseGet(() -> accountRepository.create(accountData));
    }

    private boolean isPixKeyLimitValid(Account account) {
        HolderType holderType = HolderType.NATURAL_PERSON;
        long total = pixKeyRepository.count(account.getId());
        return holderType.isValidNumberOfKeys(total);
    }

    private Either<String, Error> generateUniquePixKeyId() {
        String id = idGenerator.generate();
        if (pixKeyRepository.findById(id).isPresent()) {
            return Either.right(new Error("Erro ao gerar ID único"));
        }
        return Either.left(id);
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
