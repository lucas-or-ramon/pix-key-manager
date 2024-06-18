package dev.canoa.pixkeymanager.application;

import io.jbock.util.Either;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface GetPixKeyUseCase {
    Either<List<PixKey>, Error> execute(@NotNull @Valid GetPixKey params);
}
