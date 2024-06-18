package dev.canoa.pixkeymanager.adapters.web.rest.controller;

import dev.canoa.pixkeymanager.adapters.web.rest.request.CreatePixKeyRequest;
import dev.canoa.pixkeymanager.adapters.web.rest.response.GetPixKeyResponse;
import dev.canoa.pixkeymanager.adapters.web.rest.request.GetPixKeyRequest;
import dev.canoa.pixkeymanager.adapters.web.rest.request.UpdatePixKeyRequest;
import dev.canoa.pixkeymanager.adapters.web.rest.response.HttpResponse;
import dev.canoa.pixkeymanager.adapters.web.rest.response.PixKeyResponse;
import dev.canoa.pixkeymanager.application.PixKey;
import dev.canoa.pixkeymanager.application.CreatePixKeyUseCase;
import dev.canoa.pixkeymanager.application.DeletePixKeyUseCase;
import dev.canoa.pixkeymanager.application.GetPixKeyUseCase;
import dev.canoa.pixkeymanager.application.UpdatePixKeyUseCase;
import io.jbock.util.Either;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/pix-keys")
public class PixKeyController {

    private final GetPixKeyUseCase getPixKeyUseCase;
    private final CreatePixKeyUseCase createPixKeyUseCase;
    private final UpdatePixKeyUseCase updatePixKeyUseCase;
    private final DeletePixKeyUseCase deletePixKeyUseCase;

    @PostMapping
    public ResponseEntity<HttpResponse<String>> createPixKey(@RequestBody CreatePixKeyRequest body) {
        Either<String, Error> result = createPixKeyUseCase.execute(body.toDomain());
        return result.fold(
                data -> ResponseEntity.ok(HttpResponse.of(data, null)),
                error -> ResponseEntity.badRequest().body(HttpResponse.of(null, error.getMessage()))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpResponse<PixKeyResponse>> updatePixKey(@PathVariable String id, @RequestBody UpdatePixKeyRequest body) {
        Either<PixKey, Error> result = updatePixKeyUseCase.execute(id, body.toDomain());
        return result.fold(
                data -> ResponseEntity.ok(HttpResponse.of(PixKeyResponse.fromDomain(data), null)),
                error -> ResponseEntity.badRequest().body(HttpResponse.of(null, error.getMessage()))
        );
    }

    @GetMapping
    public ResponseEntity<HttpResponse<List<GetPixKeyResponse>>> getPixKey(GetPixKeyRequest params) {
        Either<List<PixKey>, Error> result = getPixKeyUseCase.execute(params.toDomain());
        return result.fold(
                data -> ResponseEntity.ok(HttpResponse.of(GetPixKeyResponse.fromDomain(data), null)),
                error -> ResponseEntity.badRequest().body(HttpResponse.of(null, error.getMessage()))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponse<PixKeyResponse>> deletePixKey(@PathVariable String id) {
        Either<PixKey, Error> result = deletePixKeyUseCase.execute(id);
        return result.fold(
                data -> ResponseEntity.ok(HttpResponse.of(PixKeyResponse.fromDomain(data), null)),
                error -> ResponseEntity.badRequest().body(HttpResponse.of(null, error.getMessage()))
        );
    }
}
