package dev.canoa.pixkeymanager.application;

import io.jbock.util.Either;

public interface DeletePixKeyUseCase {
    Either<PixKey, Error> execute(String id);
}
