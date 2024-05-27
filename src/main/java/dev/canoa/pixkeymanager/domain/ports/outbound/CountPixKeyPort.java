package dev.canoa.pixkeymanager.domain.ports.outbound;

import dev.canoa.pixkeymanager.domain.model.Account;
import dev.canoa.pixkeymanager.domain.model.HolderType;

public interface CountPixKeyPort {
    int count(Account account, HolderType holderType);
}
