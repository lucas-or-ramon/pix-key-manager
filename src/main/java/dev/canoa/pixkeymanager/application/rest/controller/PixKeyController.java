package dev.canoa.pixkeymanager.application.rest.controller;

import dev.canoa.pixkeymanager.application.rest.request.CreatePixKeyRequest;
import dev.canoa.pixkeymanager.application.rest.response.GetPixKeyResponse;
import dev.canoa.pixkeymanager.domain.model.PixKey;
import dev.canoa.pixkeymanager.domain.ports.inbound.CreatePixKeyUseCase;
import dev.canoa.pixkeymanager.domain.ports.inbound.GetPixKeyUseCase;
import dev.canoa.pixkeymanager.domain.ports.inbound.UpdatePixKeyUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pix-keys")
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
    public ResponseEntity<String> createPixKey(@Valid @RequestBody CreatePixKeyRequest createPixKeyRequest) {
        String id = createPixKeyUseCase.execute(createPixKeyRequest.toModel());
        return ResponseEntity.ok(id);
    }

    @GetMapping
    public ResponseEntity<GetPixKeyResponse> getPixKey() {
        PixKey pixKey = getPixKeyUseCase.getPixKey("");
        return ResponseEntity.ok(GetPixKeyResponse.builder().build());
    }

    @PutMapping
    public ResponseEntity<Void> updatePixKey(String key, @RequestBody PixKey pixKey) {
        updatePixKeyUseCase.updatePixKey(key, pixKey);
        return ResponseEntity.ok().build();
    }
}
