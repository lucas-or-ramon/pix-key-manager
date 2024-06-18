package dev.canoa.pixkeymanager.adapters.database.entity;

import dev.canoa.pixkeymanager.application.account.*;
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
public class AccountEntity {

    @Id
    String id;
    String type;
    Integer branch;
    Integer number;
    String holderName;
    String holderLastName;

    public static AccountEntity fromModel(Account account) {
        return new AccountEntity(
                account.getId(),
                account.getType().name(),
                account.getBranch(),
                account.getNumber(),
                account.getHolderName(),
                account.getHolderLastName()
        );
    }

    public Account toModel() {
        AccountType accountType = AccountType.valueOf(this.type);
        return switch (accountType) {
            case CHECKING -> CheckingAccount.builder()
                    .id(this.id)
                    .branch(this.branch)
                    .number(this.number)
                    .holderName(this.holderName)
                    .holderLastName(this.holderLastName)
                    .build();
            case SAVINGS -> new SavingsAccount();
        };
    }
}
