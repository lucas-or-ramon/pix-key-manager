package dev.canoa.pixkeymanager.adapters.database;

import dev.canoa.pixkeymanager.application.key.Key;
import dev.canoa.pixkeymanager.application.key.KeyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class KeyRepositoryImpl implements KeyRepository {
    @Override
    public Optional<Key> findByValue(String key) {
        return Optional.empty();
    }

    @Override
    public Key create(Key key) {
        return null;
    }

    @Override
    public Key update(Key key) {
        return null;
    }

    @Override
    public Key delete(String key) {
        return null;
    }
}
