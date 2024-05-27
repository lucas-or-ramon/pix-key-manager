package dev.canoa.pixkeymanager.domain.services;

import dev.canoa.pixkeymanager.domain.model.GetPixKey;
import dev.canoa.pixkeymanager.domain.model.PixKey;
import dev.canoa.pixkeymanager.domain.ports.outbound.PixKeyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetPixKeyServiceTest {

    private PixKeyRepository pixKeyRepository;
    private GetPixKeyService getPixKeyService;

    @BeforeEach
    public void setUp() {
        pixKeyRepository = mock(PixKeyRepository.class);
        getPixKeyService = new GetPixKeyService(pixKeyRepository);
    }

    @Test
    public void testGetPixKeysWithValidId() {
        GetPixKey params = mock(GetPixKey.class);
        when(params.isValid()).thenReturn(true);
        when(params.id()).thenReturn("validId");
        PixKey pixKey = mock(PixKey.class);
        when(pixKeyRepository.findByKey("validId")).thenReturn(pixKey);

        List<PixKey> result = getPixKeyService.getPixKeys(params);

        assertEquals(1, result.size());
        assertEquals(pixKey, result.get(0));
    }

    @Test
    public void testGetPixKeysWithInvalidParams() {
        GetPixKey params = mock(GetPixKey.class);
        when(params.isValid()).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            getPixKeyService.getPixKeys(params);
        });

        assertEquals("Parâmetros inválidos", exception.getMessage());
    }

    @Test
    public void testGetPixKeysWithNoIdAndValidResult() {
        GetPixKey params = mock(GetPixKey.class);
        when(params.isValid()).thenReturn(true);
        when(params.id()).thenReturn(null);
        PixKey pixKey = mock(PixKey.class);
        when(pixKeyRepository.findWith(params)).thenReturn(List.of(pixKey));

        List<PixKey> result = getPixKeyService.getPixKeys(params);

        assertEquals(1, result.size());
        assertEquals(pixKey, result.get(0));
    }

    @Test
    public void testGetPixKeysWithNoIdAndNoResult() {
        GetPixKey params = mock(GetPixKey.class);
        when(params.isValid()).thenReturn(true);
        when(params.id()).thenReturn(null);
        when(pixKeyRepository.findWith(params)).thenReturn(Collections.emptyList());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            getPixKeyService.getPixKeys(params);
        });

        assertEquals("Chave Pix não encontrada", exception.getMessage());
    }

    @Test
    public void testGetPixKeysWithNullId() {
        GetPixKey params = mock(GetPixKey.class);
        when(params.isValid()).thenReturn(true);
        when(params.id()).thenReturn(null);
        PixKey pixKey = mock(PixKey.class);
        when(pixKeyRepository.findWith(params)).thenReturn(List.of(pixKey));

        List<PixKey> result = getPixKeyService.getPixKeys(params);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(pixKey, result.get(0));
    }
}

