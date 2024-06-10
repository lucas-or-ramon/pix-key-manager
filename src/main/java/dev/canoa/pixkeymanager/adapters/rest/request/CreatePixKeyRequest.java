package dev.canoa.pixkeymanager.adapters.rest.request;

import dev.canoa.pixkeymanager.application.PixKey;
import dev.canoa.pixkeymanager.application.account.Account;
import dev.canoa.pixkeymanager.application.account.AccountType;
import dev.canoa.pixkeymanager.application.key.Key;
import dev.canoa.pixkeymanager.application.key.KeyType;
import lombok.Builder;

@Builder
public record CreatePixKeyRequest(
        String keyType,
        String keyValue,
        String accountType,
        Integer branchNumber,
        Integer accountNumber,
        String accountHolderName,
        String accountHolderLastName
) {
    public PixKey toDomain() {
        return PixKey.builder()
                .key(new Key(KeyType.from(keyType), keyValue))
                .account(Account.builder()
                        .type(AccountType.from(accountType))
                        .branch(branchNumber)
                        .number(accountNumber)
                        .holderName(accountHolderName)
                        .holderLastName(accountHolderLastName)
                        .build())
                .build();
    }
}
