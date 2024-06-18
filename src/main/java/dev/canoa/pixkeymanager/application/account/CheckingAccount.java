package dev.canoa.pixkeymanager.application.account;

import lombok.Builder;

@Builder
public class CheckingAccount extends Account {

    public CheckingAccount(String id, Integer branch, Integer number, String holderName, String holderLastName) {
        super(id, branch, number, holderName, holderLastName);
    }

    @Override
    public AccountType getType() {
        return AccountType.CHECKING;
    }
}
