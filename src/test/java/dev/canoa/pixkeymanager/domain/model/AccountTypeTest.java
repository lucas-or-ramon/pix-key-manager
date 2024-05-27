package dev.canoa.pixkeymanager.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTypeTest {
    @Test
    public void testFromValidTypeChecking() {
        AccountType accountType = AccountType.from("corrente");
        assertNotNull(accountType);
        assertEquals(AccountType.CHECKING, accountType);
    }

    @Test
    public void testFromValidTypeSavings() {
        AccountType accountType = AccountType.from("poupança");
        assertNotNull(accountType);
        assertEquals(AccountType.SAVINGS, accountType);
    }

    @Test
    public void testFromInvalidType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AccountType.from("investimento");
        });
        assertEquals("Tipo de conta inválido", exception.getMessage());
    }

    @Test
    public void testFromNullType() {
        AccountType accountType = AccountType.from(null);
        assertNull(accountType);
    }

    @Test
    public void testFromEmptyStringType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AccountType.from("");
        });
        assertEquals("Tipo de conta inválido", exception.getMessage());
    }

    @Test
    public void testFromWhitespaceStringType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AccountType.from(" ");
        });
        assertEquals("Tipo de conta inválido", exception.getMessage());
    }
}
