package dev.canoa.pixkeymanager.application.account;

public interface Account {
    String getId();
    AccountType getType();

    Integer getBranch();

    Integer getNumber();

    String getHolderName();

    String getHolderLastName();
}
