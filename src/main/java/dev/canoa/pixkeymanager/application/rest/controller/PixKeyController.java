package dev.canoa.pixkeymanager.application.rest.controller;

import dev.canoa.pixkeymanager.application.rest.request.CreatePixKeyRequest;
import dev.canoa.pixkeymanager.application.rest.request.UpdatePixKeyRequest;
import dev.canoa.pixkeymanager.application.rest.response.GetPixKeyResponse;
import dev.canoa.pixkeymanager.domain.model.PixKey;
import dev.canoa.pixkeymanager.domain.ports.inbound.CreatePixKeyUseCase;
import dev.canoa.pixkeymanager.domain.ports.inbound.GetPixKeyUseCase;
import dev.canoa.pixkeymanager.domain.ports.inbound.UpdatePixKeyUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pix-keys")
public class PixKeyController {

    private final GetPixKeyUseCase getPixKeyUseCase;
    private final CreatePixKeyUseCase createPixKeyUseCase;
    private final UpdatePixKeyUseCase updatePixKeyUseCase;

    public PixKeyController(GetPixKeyUseCase getPixKeyUseCase, CreatePixKeyUseCase createPixKeyUseCase, UpdatePixKeyUseCase updatePixKeyUseCase) {
        this.getPixKeyUseCase = getPixKeyUseCase;
        this.createPixKeyUseCase = createPixKeyUseCase;
        this.updatePixKeyUseCase = updatePixKeyUseCase;
    }

    @PostMapping
    public ResponseEntity<String> createPixKey(@RequestBody CreatePixKeyRequest body) {
        String id = createPixKeyUseCase.execute(body.toDomain());
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PixKey> updatePixKey(@PathVariable String id, @RequestBody UpdatePixKeyRequest body) {
        PixKey pixKey = updatePixKeyUseCase.execute(id, body.toDomain());
        return ResponseEntity.ok().body(pixKey);
    }

    @GetMapping
    public ResponseEntity<GetPixKeyResponse> getPixKey() {
        PixKey pixKey = getPixKeyUseCase.getPixKey("");
        return ResponseEntity.ok(GetPixKeyResponse.fromDomain(pixKey));
    }
}
