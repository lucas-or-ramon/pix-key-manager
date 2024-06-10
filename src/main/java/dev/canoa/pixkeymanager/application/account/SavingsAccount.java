package dev.canoa.pixkeymanager.application.account;

import lombok.Builder;

@Builder
public record SavingsAccount(BaseAccount baseAccount) implements Account {

    @Override
    public String getId() {
        return null;
    }

    @Override
    public AccountType getType() {
        return AccountType.SAVINGS;
    }

    @Override
    public Integer getBranch() {
        return baseAccount().branch();
    }

    @Override
    public Integer getNumber() {
        return baseAccount().number();
    }

    @Override
    public String getHolderName() {
        return baseAccount().holderName();
    }

    @Override
    public String getHolderLastName() {
        return baseAccount().holderLastName();
    }
}
