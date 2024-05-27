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
        when(pixKeyRepository.existsKeyValue(anyString())).thenReturn(false);
        when(pixKeyRepository.existsKeyType(any(KeyType.class), anyInt(), anyInt())).thenReturn(false);
        when(pixKeyRepository.create(any(PixKey.class))).thenReturn("createdId");
        when(pixKey.account()).thenReturn(mock(Account.class));
        when(pixKey.key()).thenReturn(mock(Key.class));
        when(pixKeyRepository.count(any(HolderType.class), anyInt(), anyInt())).thenReturn(0L);

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
        Key key = mock(Key.class);
        when(key.value()).thenReturn("existingKeyValue");
        when(pixKey.key()).thenReturn(key);
        when(idGenerator.generate()).thenReturn("generatedId");
        when(pixKeyRepository.existsId("generatedId")).thenReturn(false);
        when(pixKeyRepository.existsKeyValue("existingKeyValue")).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            createPixKeyService.execute(pixKey);
        });

        assertEquals("Chave Pix já cadastrada", exception.getMessage());
    }

    @Test
    public void testExecuteKeyTypeAlreadyExists() {
        CreatePixKey pixKey = mock(CreatePixKey.class);
        Key key = mock(Key.class);
        Account account = mock(Account.class);
        when(key.type()).thenReturn(KeyType.CPF);
        when(account.branch()).thenReturn(1234);
        when(account.number()).thenReturn(5678);
        when(pixKey.key()).thenReturn(key);
        when(pixKey.account()).thenReturn(account);
        when(idGenerator.generate()).thenReturn("generatedId");
        when(pixKeyRepository.existsId("generatedId")).thenReturn(false);
        when(pixKeyRepository.existsKeyValue(anyString())).thenReturn(false);
        when(pixKeyRepository.existsKeyType(KeyType.CPF, 1234, 5678)).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            createPixKeyService.execute(pixKey);
        });

        assertEquals("Tipo de chave Pix já cadastrada", exception.getMessage());
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
        when(pixKeyRepository.existsKeyValue(anyString())).thenReturn(false);
        when(pixKeyRepository.existsKeyType(any(KeyType.class), anyInt(), anyInt())).thenReturn(false);
        when(pixKeyRepository.count(any(HolderType.class), eq(1234), eq(5678))).thenReturn(21L);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            createPixKeyService.execute(pixKey);
        });

        assertEquals("Limite de chaves Pix inválido ou excedido", exception.getMessage());
    }
}