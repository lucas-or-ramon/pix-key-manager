package dev.canoa.pixkeymanager.application.services;

import dev.canoa.pixkeymanager.application.PixKey;
import dev.canoa.pixkeymanager.application.account.Account;
import dev.canoa.pixkeymanager.application.key.Key;
import dev.canoa.pixkeymanager.application.uuid.IdGenerator;
import dev.canoa.pixkeymanager.application.PixKeyRepository;
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
        PixKey pixKey = mock(PixKey.class);
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
        PixKey pixKey = mock(PixKey.class);
        when(idGenerator.generate()).thenReturn("generatedId");
        when(pixKeyRepository.existsId("generatedId")).thenReturn(true);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            createPixKeyService.execute(pixKey);
        });

        assertEquals("Id já existe", exception.getMessage());
    }

    @Test
    public void testExecuteKeyAlreadyExists() {
        PixKey pixKey = mock(PixKey.class);
        Account account = mock(Account.class);
        Key key = mock(Key.class);
        when(key.getValue()).thenReturn("existingKeyValue");
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
        PixKey pixKey = mock(PixKey.class);
        Account account = mock(Account.class);
        when(account.getBranch()).thenReturn(1234);
        when(account.getNumber()).thenReturn(5678);
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