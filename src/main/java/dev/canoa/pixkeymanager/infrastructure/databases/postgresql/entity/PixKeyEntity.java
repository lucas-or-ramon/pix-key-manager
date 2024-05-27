package dev.canoa.pixkeymanager.infrastructure.databases.postgresql.entity;

import dev.canoa.pixkeymanager.domain.model.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    String inclusionDateTime;
    String deactivationDateTime;

    public static PixKeyEntity fromModel(PixKey pixKey) {
        return new PixKeyEntity(
                pixKey.id(),
                pixKey.key().type().name(),
                pixKey.key().value(),
                pixKey.account().type().name(),
                pixKey.account().branch(),
                pixKey.account().number(),
                pixKey.account().holderName(),
                pixKey.account().holderLastName(),
                pixKey.inclusionDateTime(),
                null
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
                .build();
    }
}
