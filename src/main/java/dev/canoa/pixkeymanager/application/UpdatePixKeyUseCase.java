package dev.canoa.pixkeymanager.application;

import dev.canoa.pixkeymanager.application.account.Account;
import io.jbock.util.Either;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UpdatePixKeyUseCase {
    Either<PixKey, Error> execute(@NotBlank String id, @Valid Account account);
}
