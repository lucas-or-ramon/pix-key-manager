package dev.canoa.pixkeymanager.infrastructure.databases.postgresql;

import dev.canoa.pixkeymanager.domain.model.HolderType;
import dev.canoa.pixkeymanager.domain.ports.outbound.CountPixKeyPort;
import org.springframework.stereotype.Component;

@Component
public class CountPixKeyAdaper implements CountPixKeyPort {

    @Override
    public int count(int accountNumber, int branchNumber, HolderType holderType) {
        return 0;
    }
}
