package dev.canoa.pixkeymanager.application.model;

import dev.canoa.pixkeymanager.application.account.Account;
import dev.canoa.pixkeymanager.application.account.AccountType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountTest {
    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidAccount() {
        Account account = Account.builder()
                .type(AccountType.CHECKING)
                .branch(1234)
                .number(12345678)
                .holderName("João")
                .holderLastName("Silva")
                .build();
        Set<ConstraintViolation<Account>> violations = validator.validate(account);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testNullAccountType() {
        Account account = Account.builder()
                .branch(1234)
                .number(12345678)
                .holderName("João")
                .holderLastName("Silva")
                .build();
        Set<ConstraintViolation<Account>> violations = validator.validate(account);
        assertEquals(1, violations.size());
        assertEquals("Tipo de Conta (accountType) é obrigatório", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidBranchNumber() {
        Account account = Account.builder()
                .type(AccountType.CHECKING)
                .branch(0)
                .number(12345678)
                .holderName("João")
                .holderLastName("Silva")
                .build();
        Set<ConstraintViolation<Account>> violations = validator.validate(account);
        assertEquals(1, violations.size());
        assertEquals("Número da Agência (branchNumber) deve ser maior que 0", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullBranchNumber() {
        Account account = Account.builder()
                .type(AccountType.CHECKING)
                .number(12345678)
                .holderName("João")
                .holderLastName("Silva")
                .build();
        Set<ConstraintViolation<Account>> violations = validator.validate(account);
        assertEquals(1, violations.size());
        assertEquals("Número da Agência (branchNumber) é obrigatório", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidAccountNumber() {
        Account account = Account.builder()
                .type(AccountType.CHECKING)
                .branch(1234)
                .number(0)
                .holderName("João")
                .holderLastName("Silva")
                .build();
        Set<ConstraintViolation<Account>> violations = validator.validate(account);
        assertEquals(1, violations.size());
        assertEquals("Número da Conta (accountNumber) deve ser maior que 0", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullAccountNumber() {
        Account account = Account.builder()
                .type(AccountType.CHECKING)
                .branch(1234)
                .holderName("João")
                .holderLastName("Silva")
                .build();
        Set<ConstraintViolation<Account>> violations = validator.validate(account);
        assertEquals(1, violations.size());
        assertEquals("Número da Conta (accountNumber) é obrigatório", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidAccountHolderName() {
        Account account = Account.builder()
                .type(AccountType.CHECKING)
                .branch(1234)
                .number(12345678)
                .holderName("")
                .holderLastName("Silva")
                .build();
        Set<ConstraintViolation<Account>> violations = validator.validate(account);
        assertEquals(2, violations.size());
    }

    @Test
    public void testLongAccountHolderName() {
        Account account = Account.builder()
                .type(AccountType.CHECKING)
                .branch(1234)
                .number(12345678)
                .holderName("a".repeat(31))
                .holderLastName("Silva")
                .build();
        Set<ConstraintViolation<Account>> violations = validator.validate(account);
        assertEquals(1, violations.size());
        assertEquals("Nome do Titular da Conta (accountHolderName) deve ter entre 1 e 30 caracteres", violations.iterator().next().getMessage());
    }

    @Test
    public void testLongAccountHolderLastName() {
        Account account = Account.builder()
                .type(AccountType.CHECKING)
                .branch(1234)
                .number(12345678)
                .holderName("João")
                .holderLastName("a".repeat(46))
                .build();
        Set<ConstraintViolation<Account>> violations = validator.validate(account);
        assertEquals(1, violations.size());
        assertEquals("Sobrenome do Titular da Conta (accountHolderLastName) deve ter no máximo 45 caracteres", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullAccountHolderLastName() {
        Account account = Account.builder()
                .type(AccountType.CHECKING)
                .branch(1234)
                .number(12345678)
                .holderName("João")
                .build();
        Set<ConstraintViolation<Account>> violations = validator.validate(account);
        assertTrue(violations.isEmpty());
    }
}
