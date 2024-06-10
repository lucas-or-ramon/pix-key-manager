package dev.canoa.pixkeymanager.adapters.rest.controller;

import dev.canoa.pixkeymanager.adapters.rest.request.CreatePixKeyRequest;
import dev.canoa.pixkeymanager.adapters.rest.response.GetPixKeyResponse;
import dev.canoa.pixkeymanager.adapters.rest.request.GetPixKeyRequest;
import dev.canoa.pixkeymanager.adapters.rest.request.UpdatePixKeyRequest;
import dev.canoa.pixkeymanager.adapters.rest.response.PixKeyResponse;
import dev.canoa.pixkeymanager.application.PixKey;
import dev.canoa.pixkeymanager.application.CreatePixKeyUseCase;
import dev.canoa.pixkeymanager.application.DeletePixKeyUseCase;
import dev.canoa.pixkeymanager.application.GetPixKeyUseCase;
import dev.canoa.pixkeymanager.application.UpdatePixKeyUseCase;
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
    public ResponseEntity<String> createPixKey(@RequestBody CreatePixKeyRequest body) {
        String id = createPixKeyUseCase.execute(body.toDomain());
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PixKeyResponse> updatePixKey(@PathVariable String id, @RequestBody UpdatePixKeyRequest body) {
        PixKey pixKey = updatePixKeyUseCase.execute(id, body.toDomain());
        return ResponseEntity.ok().body(PixKeyResponse.fromDomain(pixKey));
    }

    @GetMapping
    public ResponseEntity<List<GetPixKeyResponse>> getPixKey(GetPixKeyRequest params) {
        List<PixKey> pixKeys = getPixKeyUseCase.execute(params.toDomain());
        return ResponseEntity.ok(GetPixKeyResponse.fromDomain(pixKeys));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PixKeyResponse> deletePixKey(@PathVariable String id) {
        PixKey pixKey = deletePixKeyUseCase.execute(id);
        return ResponseEntity.ok().body(PixKeyResponse.fromDomain(pixKey));
    }
}
