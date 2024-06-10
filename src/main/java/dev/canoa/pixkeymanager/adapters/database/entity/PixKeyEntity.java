package dev.canoa.pixkeymanager.adapters.database.entity;

import dev.canoa.pixkeymanager.application.PixKey;
import dev.canoa.pixkeymanager.application.account.Account;
import dev.canoa.pixkeymanager.application.account.AccountType;
import dev.canoa.pixkeymanager.application.key.Key;
import dev.canoa.pixkeymanager.application.key.KeyType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PixKeyEntity {
    @Id
    String id;
    String keyType;
    String keyValue;
    String accountType;
    Integer branchNumber;
    Integer accountNumber;
    String accountHolderName;
    String accountHolderLastName;
    LocalDateTime inclusionDateTime;
    LocalDateTime deactivationDateTime;

    public static PixKeyEntity fromModel(PixKey pixKey) {
        return new PixKeyEntity(
                pixKey.id(),
                pixKey.key().getType().name(),
                pixKey.key().getValue(),
                pixKey.account().getType().name(),
                pixKey.account().getBranch(),
                pixKey.account().getNumber(),
                pixKey.account().getHolderName(),
                pixKey.account().getHolderLastName(),
                pixKey.inclusionDateTime(),
                pixKey.deactivationDateTime()
        );
    }

    public PixKey toModel() {
        Key key = new Key(KeyType.valueOf(this.keyType), this.keyValue);
        Account account = Account.builder()
                .type(AccountType.valueOf(this.accountType))
                .branch(this.branchNumber)
                .number(this.accountNumber)
                .holderName(this.accountHolderName)
                .holderLastName(this.accountHolderLastName)
                .build();

        return PixKey.builder()
                .id(this.id)
                .key(key)
                .account(account)
                .inclusionDateTime(this.inclusionDateTime)
                .deactivationDateTime(this.deactivationDateTime)
                .build();
    }
}
