package dev.canoa.pixkeymanager.application.key;

import dev.canoa.pixkeymanager.application.key.Key;

import java.util.Optional;

public interface KeyRepository {
    Optional<Key> findByValue(String key);
    Key create(Key key);
    Key update(Key key);
    Key delete(String key);
}
