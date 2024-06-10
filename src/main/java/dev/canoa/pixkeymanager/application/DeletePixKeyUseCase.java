package dev.canoa.pixkeymanager.application;

import dev.canoa.pixkeymanager.application.PixKey;

public interface DeletePixKeyUseCase {
    PixKey execute(String id);
}
