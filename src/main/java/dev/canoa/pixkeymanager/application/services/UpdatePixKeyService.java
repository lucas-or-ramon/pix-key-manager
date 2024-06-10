package dev.canoa.pixkeymanager.application.services;

import dev.canoa.pixkeymanager.application.account.Account;
import dev.canoa.pixkeymanager.application.account.AccountRepository;
import dev.canoa.pixkeymanager.application.account.HolderType;
import dev.canoa.pixkeymanager.application.PixKey;
import dev.canoa.pixkeymanager.application.UpdatePixKeyUseCase;
import dev.canoa.pixkeymanager.application.PixKeyRepository;
import dev.canoa.pixkeymanager.application.key.KeyRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdatePixKeyService implements UpdatePixKeyUseCase {

    private final KeyRepository keyRepository;
    private final PixKeyRepository pixKeyRepository;
    private final AccountRepository accountRepository;

    @Override
    public PixKey execute(String id, Account account) {
        PixKey existingPixKey = this.getExistingPixKey(id);

        Account existingAccount = this.getExistingAccount(account.getBranch(), account.getNumber());
        validatePixKeyLimit(existingAccount);

        PixKey newPixKey = this.getNewPixKey(existingPixKey, existingAccount);

        return pixKeyRepository.update(newPixKey);
    }

    private PixKey getExistingPixKey(String id) {
        return pixKeyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Chave Pix não encontrada"));
    }

    private Account getExistingAccount(Integer branch, Integer number) {
        return accountRepository.findByBranchAndAccountNumber(branch, number)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
    }

    private void validatePixKeyLimit(Account account) {
        HolderType holderType = HolderType.NATURAL_PERSON;
        long total = pixKeyRepository.count(account.getId());
        if (!holderType.isValidNumberOfKeys(total)) {
            throw new IllegalArgumentException("Limite de chaves Pix inválido ou excedido");
        }
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
