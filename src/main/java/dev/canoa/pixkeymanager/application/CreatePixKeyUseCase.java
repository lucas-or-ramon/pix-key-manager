package dev.canoa.pixkeymanager.application;

import io.jbock.util.Either;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CreatePixKeyUseCase {
    Either<String, Error> execute(@Valid PixKey pixKey);
}
