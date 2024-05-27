package dev.canoa.pixkeymanager.domain.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class KeyTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidKey() {
        Key key = new Key(KeyType.CPF, "12345678909");

        Set<ConstraintViolation<Key>> violations = validator.validate(key);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testNullKeyType() {
        Key key = new Key(null, "12345678909");

        Set<ConstraintViolation<Key>> violations = validator.validate(key);
        assertEquals(1, violations.size());
        assertEquals("Tipo de Chave Pix (keyType) é obrigatório", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullKeyValue() {
        Key key = new Key(KeyType.CPF, null);

        Set<ConstraintViolation<Key>> violations = validator.validate(key);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Valor da Chave Pix (keyValue) é obrigatório")));
    }

    @Test
    public void testEmptyKeyValue() {
        Key key = new Key(KeyType.CPF, "");

        Set<ConstraintViolation<Key>> violations = validator.validate(key);
        assertEquals(2, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Valor da Chave Pix (keyValue) é obrigatório")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Valor da Chave Pix (keyValue) deve ter entre 1 e 77 caracteres")));
    }

    @Test
    public void testKeyValueTooShort() {
        Key key = new Key(KeyType.CPF, "");

        Set<ConstraintViolation<Key>> violations = validator.validate(key);
        assertEquals(2, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Valor da Chave Pix (keyValue) é obrigatório")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Valor da Chave Pix (keyValue) deve ter entre 1 e 77 caracteres")));
    }

    @Test
    public void testKeyValueTooLong() {
        String longValue = "a".repeat(78);
        Key key = new Key(KeyType.CPF, longValue);

        Set<ConstraintViolation<Key>> violations = validator.validate(key);
        assertEquals(1, violations.size());
        assertEquals("Valor da Chave Pix (keyValue) deve ter entre 1 e 77 caracteres", violations.iterator().next().getMessage());
    }

    @Test
    public void testValidKeyValueLengths() {
        Key keyMinLength = new Key(KeyType.CPF, "1");
        Key keyMaxLength = new Key(KeyType.CPF, "a".repeat(77));

        Set<ConstraintViolation<Key>> violationsMin = validator.validate(keyMinLength);
        Set<ConstraintViolation<Key>> violationsMax = validator.validate(keyMaxLength);

        assertTrue(violationsMin.isEmpty());
        assertTrue(violationsMax.isEmpty());
    }
}
