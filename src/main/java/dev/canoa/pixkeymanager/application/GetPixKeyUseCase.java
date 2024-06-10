package dev.canoa.pixkeymanager.application;

import dev.canoa.pixkeymanager.application.GetPixKey;
import dev.canoa.pixkeymanager.application.PixKey;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface GetPixKeyUseCase {
    List<PixKey> execute(@NotNull @Valid GetPixKey params);
}
