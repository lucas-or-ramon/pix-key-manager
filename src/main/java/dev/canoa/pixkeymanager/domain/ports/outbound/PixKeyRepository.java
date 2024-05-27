package dev.canoa.pixkeymanager.domain.ports.outbound;

import dev.canoa.pixkeymanager.domain.model.GetPixKey;
import dev.canoa.pixkeymanager.domain.model.KeyType;
import dev.canoa.pixkeymanager.domain.model.PixKey;

import java.util.List;

public interface PixKeyRepository {
    PixKey findById(String id);

    List<PixKey> findWith(GetPixKey params);

    long count(Integer branchNumber, Integer accountNumber);

    String create(PixKey pixKey);

    PixKey findByKeyValue(String key);

    boolean existsId(String id);

    PixKey update(PixKey pixKey);
}
