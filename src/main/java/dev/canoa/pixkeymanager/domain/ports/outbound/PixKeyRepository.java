package dev.canoa.pixkeymanager.domain.ports.outbound;

import dev.canoa.pixkeymanager.domain.model.GetPixKey;
import dev.canoa.pixkeymanager.domain.model.HolderType;
import dev.canoa.pixkeymanager.domain.model.KeyType;
import dev.canoa.pixkeymanager.domain.model.PixKey;

import java.util.List;

public interface PixKeyRepository {
    PixKey findByKey(String key);

    List<PixKey> findWith(GetPixKey params);

    long count(HolderType holderType, Integer branchNumber, Integer accountNumber);

    String create(PixKey pixKey);

    boolean existsKeyValue(String key);

    boolean existsKeyType(KeyType type, Integer branchNumber, Integer accountNumber);

    boolean existsId(String id);

    PixKey update(PixKey pixKey);
}
