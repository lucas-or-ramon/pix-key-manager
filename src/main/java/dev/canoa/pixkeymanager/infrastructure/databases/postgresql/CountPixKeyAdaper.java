package dev.canoa.pixkeymanager.infrastructure.databases.postgresql;

import dev.canoa.pixkeymanager.domain.model.Account;
import dev.canoa.pixkeymanager.domain.model.HolderType;
import dev.canoa.pixkeymanager.domain.ports.outbound.CountPixKeyPort;
import org.springframework.stereotype.Component;

@Component
public class CountPixKeyAdaper implements CountPixKeyPort {

    @Override
    public int count(Account account, HolderType holderType) {
        return 0;
    }
}
