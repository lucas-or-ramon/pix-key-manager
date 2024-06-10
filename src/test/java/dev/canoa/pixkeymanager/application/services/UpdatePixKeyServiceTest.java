package dev.canoa.pixkeymanager.application.services;

import dev.canoa.pixkeymanager.application.PixKey;
import dev.canoa.pixkeymanager.application.account.Account;
import dev.canoa.pixkeymanager.application.PixKeyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UpdatePixKeyServiceTest {

    private PixKeyRepository pixKeyRepository;
    private UpdatePixKeyService updatePixKeyService;

    @BeforeEach
    public void setUp() {
        pixKeyRepository = mock(PixKeyRepository.class);
        updatePixKeyService = new UpdatePixKeyService(pixKeyRepository);
    }

    @Test
    public void whenExecuteWithExistingPixKey_thenUpdateIsSuccessful() {
        PixKey existingPixKey = mock(PixKey.class);
        Account account = mock(Account.class);
        when(pixKeyRepository.findById(anyString())).thenReturn(existingPixKey);
        when(pixKeyRepository.update(any(PixKey.class))).thenReturn(existingPixKey);

        PixKey result = updatePixKeyService.execute("existingId", account);

        assertEquals(existingPixKey, result);
    }

    @Test
    public void whenExecuteWithNonExistingPixKey_thenExceptionIsThrown() {
        Account account = mock(Account.class);
        when(pixKeyRepository.findById(anyString())).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> updatePixKeyService.execute("nonExistingId", account));
    }
}
