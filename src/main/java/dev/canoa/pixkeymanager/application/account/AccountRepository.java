package dev.canoa.pixkeymanager.application.account;

import dev.canoa.pixkeymanager.application.account.Account;

import java.util.Optional;

public interface AccountRepository {
    boolean exists(String id);
    Optional<Account> findById(String id);
    Optional<Account> findByBranchAndAccountNumber(Integer branchNumber, Integer accountNumber);
    Account create(Account account);
    Account update(Account account);
    Account delete(String id);
}
