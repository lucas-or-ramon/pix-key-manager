package dev.canoa.pixkeymanager.domain.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GetPixKeyTest {

    private final Validator validator;

    public GetPixKeyTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    public void testValidGetPixKey() {
        GetPixKey getPixKey = GetPixKey.builder()
                .keyType(KeyType.CPF)
                .branchNumber(1234)
                .accountNumber(12345678)
                .accountHolderName("João")
                .inclusionDateTime("2023-05-01")
                .build();

        Set<ConstraintViolation<GetPixKey>> violations = validator.validate(getPixKey);
        assertTrue(violations.isEmpty());
        assertTrue(getPixKey.isValid());
    }

    @Test
    public void testNullId() {
        GetPixKey getPixKey = GetPixKey.builder()
                .id(null)
                .keyType(KeyType.CPF)
                .branchNumber(1234)
                .accountNumber(12345678)
                .accountHolderName("João")
                .inclusionDateTime("2023-05-01")
                .build();

        Set<ConstraintViolation<GetPixKey>> violations = validator.validate(getPixKey);
        assertTrue(violations.isEmpty());
        assertTrue(getPixKey.isValid());
    }

    @Test
    public void testEmptyId() {
        GetPixKey getPixKey = GetPixKey.builder()
                .id("")
                .keyType(KeyType.CPF)
                .branchNumber(1234)
                .accountNumber(12345678)
                .accountHolderName("João")
                .inclusionDateTime("2023-05-01")
                .build();

        Set<ConstraintViolation<GetPixKey>> violations = validator.validate(getPixKey);
        assertFalse(violations.isEmpty());
        Exception exception = assertThrows(IllegalArgumentException.class, getPixKey::isValid);
        assertEquals("Se informar o ID para consulta, nenhum outro filtro pode ser aceito.", exception.getMessage());
    }

    @Test
    public void testNonEmptyIdWithOtherFields() {
        GetPixKey getPixKey = GetPixKey.builder()
                .id("123")
                .keyType(KeyType.CPF)
                .branchNumber(1234)
                .accountNumber(12345678)
                .accountHolderName("João")
                .inclusionDateTime("2023-05-01")
                .build();

        Exception exception = assertThrows(IllegalArgumentException.class, getPixKey::isValid);
        assertEquals("Se informar o ID para consulta, nenhum outro filtro pode ser aceito.", exception.getMessage());
    }

    @Test
    public void testNullOrEmptyBranchOrAccountNumber() {
        GetPixKey getPixKey = GetPixKey.builder()
                .keyType(KeyType.CPF)
                .accountNumber(12345678)
                .accountHolderName("João")
                .inclusionDateTime("2023-05-01")
                .build();

        Exception exception = assertThrows(IllegalArgumentException.class, getPixKey::isValid);
        assertEquals("Numero da agência e número da conta devem ser informados juntos.", exception.getMessage());
    }

    @Test
    public void testInvalidKeyType() {
        GetPixKey getPixKey = GetPixKey.builder()
                .keyType(null)
                .branchNumber(1234)
                .accountNumber(12345678)
                .accountHolderName("João")
                .inclusionDateTime("2023-05-01")
                .build();

        Set<ConstraintViolation<GetPixKey>> violations = validator.validate(getPixKey);
        assertTrue(violations.isEmpty());
        assertTrue(getPixKey.isValid());
    }

    @Test
    public void testInvalidBranchNumber() {
        GetPixKey getPixKey = GetPixKey.builder()
                .keyType(KeyType.CPF)
                .branchNumber(0)
                .accountNumber(12345678)
                .accountHolderName("João")
                .inclusionDateTime("2023-05-01")
                .build();

        Set<ConstraintViolation<GetPixKey>> violations = validator.validate(getPixKey);
        assertEquals(1, violations.size());
        assertEquals("Número da Agência (branchNumber) deve ser maior que 0", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidAccountNumber() {
        GetPixKey getPixKey = GetPixKey.builder()
                .keyType(KeyType.CPF)
                .branchNumber(1234)
                .accountNumber(0)
                .accountHolderName("João")
                .inclusionDateTime("2023-05-01")
                .build();

        Set<ConstraintViolation<GetPixKey>> violations = validator.validate(getPixKey);
        assertEquals(1, violations.size());
        assertEquals("Número da Conta (accountNumber) deve ser maior que 0", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullAccountHolderName() {
        GetPixKey getPixKey = GetPixKey.builder()
                .keyType(KeyType.CPF)
                .branchNumber(1234)
                .accountNumber(12345678)
                .accountHolderName(null)
                .inclusionDateTime("2023-05-01")
                .build();

        Set<ConstraintViolation<GetPixKey>> violations = validator.validate(getPixKey);
        assertTrue(violations.isEmpty());
        assertTrue(getPixKey.isValid());
    }

    @Test
    public void testEmptyAccountHolderName() {
        GetPixKey getPixKey = GetPixKey.builder()
                .keyType(KeyType.CPF)
                .branchNumber(1234)
                .accountNumber(12345678)
                .accountHolderName("")
                .inclusionDateTime("2023-05-01")
                .build();

        Set<ConstraintViolation<GetPixKey>> violations = validator.validate(getPixKey);
        assertEquals(2, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Nome do Titular da Conta (accountHolderName) não pode ser vazio")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Nome do Titular da Conta (accountHolderName) deve ter entre 1 e 30 caracteres")));
    }

    @Test
    public void testInclusionAndDeactivationDatesTogether() {
        GetPixKey getPixKey = GetPixKey.builder()
                .keyType(KeyType.CPF)
                .branchNumber(1234)
                .accountNumber(12345678)
                .accountHolderName("João")
                .inclusionDateTime("2023-05-01")
                .deactivationDateTime("2023-05-02")
                .build();

        Exception exception = assertThrows(IllegalArgumentException.class, getPixKey::isValid);
        assertEquals("Não permitir a combinação de filtros Data de inclusão da chave e Data da inativação da chave. Somente um ou outro.", exception.getMessage());
    }

    @Test
    public void testInclusionDateOnly() {
        GetPixKey getPixKey = GetPixKey.builder()
                .keyType(KeyType.CPF)
                .branchNumber(1234)
                .accountNumber(12345678)
                .accountHolderName("João")
                .inclusionDateTime("2023-05-01")
                .deactivationDateTime(null)
                .build();

        Set<ConstraintViolation<GetPixKey>> violations = validator.validate(getPixKey);
        assertTrue(violations.isEmpty());
        assertTrue(getPixKey.isValid());
    }

    @Test
    public void testDeactivationDateOnly() {
        GetPixKey getPixKey = GetPixKey.builder()
                .keyType(KeyType.CPF)
                .branchNumber(1234)
                .accountNumber(12345678)
                .accountHolderName("João")
                .inclusionDateTime(null)
                .deactivationDateTime("2023-05-02")
                .build();

        Set<ConstraintViolation<GetPixKey>> violations = validator.validate(getPixKey);
        assertTrue(violations.isEmpty());
        assertTrue(getPixKey.isValid());
    }

    @Test
    public void testEmptyInclusionDateTime() {
        GetPixKey getPixKey = GetPixKey.builder()
                .keyType(KeyType.CPF)
                .branchNumber(1234)
                .accountNumber(12345678)
                .accountHolderName("João")
                .inclusionDateTime("")
                .build();

        Set<ConstraintViolation<GetPixKey>> violations = validator.validate(getPixKey);
        assertEquals(1, violations.size());
        assertEquals("Data de inclusão da chave (inclusionDateTime) não pode ser vazia", violations.iterator().next().getMessage());
    }

    @Test
    public void testEmptyDeactivationDateTime() {
        GetPixKey getPixKey = GetPixKey.builder()
                .keyType(KeyType.CPF)
                .branchNumber(1234)
                .accountNumber(12345678)
                .accountHolderName("João")
                .deactivationDateTime("")
                .build();

        Set<ConstraintViolation<GetPixKey>> violations = validator.validate(getPixKey);
        assertEquals(1, violations.size());
        assertEquals("Data de inativação da chave (deactivationDateTime) não pode ser vazia", violations.iterator().next().getMessage());
    }
}
