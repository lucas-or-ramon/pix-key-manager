package dev.canoa.pixkeymanager.domain.services;

import dev.canoa.pixkeymanager.domain.model.*;
import dev.canoa.pixkeymanager.domain.ports.outbound.IdGenerator;
import dev.canoa.pixkeymanager.domain.ports.outbound.PixKeyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreatePixKeyServiceTest {

    private IdGenerator idGenerator;
    private PixKeyRepository pixKeyRepository;
    private CreatePixKeyService createPixKeyService;

    @BeforeEach
    public void setUp() {
        idGenerator = mock(IdGenerator.class);
        pixKeyRepository = mock(PixKeyRepository.class);
        createPixKeyService = new CreatePixKeyService(idGenerator, pixKeyRepository);
    }

    @Test
    public void testExecuteSuccess() {
        CreatePixKey pixKey = mock(CreatePixKey.class);
        when(idGenerator.generate()).thenReturn("generatedId");
        when(pixKeyRepository.existsId("generatedId")).thenReturn(false);
        when(pixKeyRepository.findByKeyValue(anyString())).thenReturn(null);
        when(pixKeyRepository.create(any(PixKey.class))).thenReturn("createdId");
        when(pixKey.account()).thenReturn(mock(Account.class));
        when(pixKey.key()).thenReturn(mock(Key.class));
        when(pixKeyRepository.count(anyInt(), anyInt())).thenReturn(0L);

        String result = createPixKeyService.execute(pixKey);

        assertEquals("createdId", result);
    }

    @Test
    public void testExecuteIdAlreadyExists() {
        CreatePixKey pixKey = mock(CreatePixKey.class);
        when(idGenerator.generate()).thenReturn("generatedId");
        when(pixKeyRepository.existsId("generatedId")).thenReturn(true);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            createPixKeyService.execute(pixKey);
        });

        assertEquals("Id já existe", exception.getMessage());
    }

    @Test
    public void testExecuteKeyAlreadyExists() {
        CreatePixKey pixKey = mock(CreatePixKey.class);
        Account account = mock(Account.class);
        Key key = mock(Key.class);
        when(key.value()).thenReturn("existingKeyValue");
        when(pixKey.key()).thenReturn(key);
        when(pixKey.account()).thenReturn(account);
        when(idGenerator.generate()).thenReturn("generatedId");
        when(pixKeyRepository.existsId("generatedId")).thenReturn(false);
        when(pixKeyRepository.findByKeyValue("existingKeyValue")).thenReturn(mock(PixKey.class));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            createPixKeyService.execute(pixKey);
        });

        assertEquals("Chave Pix já cadastrada", exception.getMessage());
    }

    @Test
    public void testExecuteInvalidNumberOfKeys() {
        CreatePixKey pixKey = mock(CreatePixKey.class);
        Account account = mock(Account.class);
        when(account.branch()).thenReturn(1234);
        when(account.number()).thenReturn(5678);
        when(pixKey.account()).thenReturn(account);
        when(pixKey.key()).thenReturn(mock(Key.class));
        when(idGenerator.generate()).thenReturn("generatedId");
        when(pixKeyRepository.existsId("generatedId")).thenReturn(false);
        when(pixKeyRepository.findByKeyValue(anyString())).thenReturn(null);
        when(pixKeyRepository.count(eq(1234), eq(5678))).thenReturn(21L);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            createPixKeyService.execute(pixKey);
        });

        assertEquals("Limite de chaves Pix inválido ou excedido", exception.getMessage());
    }
}