package dev.canoa.pixkeymanager.application.services;

import dev.canoa.pixkeymanager.application.account.Account;
import dev.canoa.pixkeymanager.application.account.AccountRepository;
import dev.canoa.pixkeymanager.application.account.HolderType;
import dev.canoa.pixkeymanager.application.PixKey;
import dev.canoa.pixkeymanager.application.UpdatePixKeyUseCase;
import dev.canoa.pixkeymanager.application.PixKeyRepository;
import dev.canoa.pixkeymanager.application.key.KeyRepository;
import io.jbock.util.Either;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UpdatePixKeyService implements UpdatePixKeyUseCase {

    private final KeyRepository keyRepository;
    private final PixKeyRepository pixKeyRepository;
    private final AccountRepository accountRepository;

    @Override
    public Either<PixKey, Error> execute(String id, Account account) {
        Optional<PixKey> existingPixKey = this.getExistingPixKey(id);
        if (existingPixKey.isEmpty()) {
            return Either.right(new Error("Chave Pix não encontrada"));
        }

        Optional<Account> existingAccount = this.getExistingAccount(account.getBranch(), account.getNumber());
        if (existingAccount.isEmpty()) {
            return Either.right(new Error("Conta não encontrada"));
        }

        if (!isPixKeyLimitValid(existingAccount.get())) {
            return Either.right(new Error("Limite de chaves atingido"));
        }

        PixKey newPixKey = this.getNewPixKey(existingPixKey.get(), existingAccount.get());
        return Either.left(pixKeyRepository.update(newPixKey));
    }

    private Optional<PixKey> getExistingPixKey(String id) {
        return pixKeyRepository.findById(id);
    }

    private Optional<Account> getExistingAccount(Integer branch, Integer number) {
        return accountRepository.findByBranchAndAccountNumber(branch, number);
    }

    private boolean isPixKeyLimitValid(Account account) {
        HolderType holderType = HolderType.NATURAL_PERSON;
        long total = pixKeyRepository.count(account.getId());
        return holderType.isValidNumberOfKeys(total);
    }

    private PixKey getNewPixKey(PixKey pixKey, Account account) {
        return PixKey.builder()
                .id(pixKey.id())
                .key(pixKey.key())
                .account(account)
                .inclusionDateTime(pixKey.inclusionDateTime())
                .build();
    }
}
