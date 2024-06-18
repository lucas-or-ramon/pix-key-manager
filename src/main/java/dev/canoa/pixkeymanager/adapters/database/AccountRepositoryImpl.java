package dev.canoa.pixkeymanager.adapters.database;

import dev.canoa.pixkeymanager.application.account.Account;
import dev.canoa.pixkeymanager.application.account.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
    @Override
    public boolean exists(String id) {
        return false;
    }

    @Override
    public Optional<Account> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<Account> findByBranchAndAccountNumber(Integer branchNumber, Integer accountNumber) {
        return Optional.empty();
    }

    @Override
    public Account create(Account account) {
        return null;
    }

    @Override
    public Account update(Account account) {
        return null;
    }

    @Override
    public Account delete(String id) {
        return null;
    }
}
