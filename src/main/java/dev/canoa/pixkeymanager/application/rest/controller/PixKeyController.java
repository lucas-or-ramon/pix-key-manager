package dev.canoa.pixkeymanager.application.rest.controller;

import dev.canoa.pixkeymanager.application.rest.controller.response.CreatePixKeyResponse;
import dev.canoa.pixkeymanager.application.rest.controller.response.GetPixKeyResponse;
import dev.canoa.pixkeymanager.domain.model.PixKey;
import dev.canoa.pixkeymanager.domain.ports.inbound.CreatePixKeyUseCase;
import dev.canoa.pixkeymanager.domain.ports.inbound.GetPixKeyUseCase;
import dev.canoa.pixkeymanager.domain.ports.inbound.UpdatePixKeyUseCase;
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

    @GetMapping
    public ResponseEntity<GetPixKeyResponse> getPixKey() {
        PixKey pixKey = getPixKeyUseCase.getPixKey("");
        return ResponseEntity.ok(new GetPixKeyResponse(""));
    }

    @PostMapping
    public ResponseEntity<CreatePixKeyResponse> createPixKey(@RequestBody PixKey pixKey) {
        createPixKeyUseCase.createPixKey(pixKey);
        return ResponseEntity.ok(new CreatePixKeyResponse());
    }

    @PutMapping
    public ResponseEntity<Void> updatePixKey(String key, @RequestBody PixKey pixKey) {
        updatePixKeyUseCase.updatePixKey(key, pixKey);
        return ResponseEntity.ok().build();
    }
}
