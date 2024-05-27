package dev.canoa.pixkeymanager.domain.ports.outbound;

import dev.canoa.pixkeymanager.domain.model.HolderType;

public interface CountPixKeyPort {
    int count(int accountNumber, int branchNumber, HolderType holderType);
}
