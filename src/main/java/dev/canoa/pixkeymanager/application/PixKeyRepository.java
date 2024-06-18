package dev.canoa.pixkeymanager.application;

import java.util.List;
import java.util.Optional;

public interface PixKeyRepository {
    Optional<PixKey> findById(String id);

    List<PixKey> findWith(GetPixKey params);

    PixKey create(PixKey pixKey);

    PixKey update(PixKey pixKey);

    long count(String accountId);
}
