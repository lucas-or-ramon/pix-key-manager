package dev.canoa.pixkeymanager.application.model;

import dev.canoa.pixkeymanager.application.account.HolderType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HolderTypeTest {

    @Test
    public void testDescriptionNaturalPerson() {
        assertEquals("pessoa física", HolderType.NATURAL_PERSON.getDescription());
    }

    @Test
    public void testDescriptionLegalPerson() {
        assertEquals("pessoa jurídica", HolderType.LEGAL_PERSON.getDescription());
    }

    @Test
    public void testValidNumberOfKeysForNaturalPerson() {
        assertTrue(HolderType.NATURAL_PERSON.isValidNumberOfKeys(0));
        assertTrue(HolderType.NATURAL_PERSON.isValidNumberOfKeys(3));
        assertTrue(HolderType.NATURAL_PERSON.isValidNumberOfKeys(5));
    }

    @Test
    public void testInvalidNumberOfKeysForNaturalPerson() {
        assertFalse(HolderType.NATURAL_PERSON.isValidNumberOfKeys(-1));
        assertFalse(HolderType.NATURAL_PERSON.isValidNumberOfKeys(6));
    }

    @Test
    public void testValidNumberOfKeysForLegalPerson() {
        assertTrue(HolderType.LEGAL_PERSON.isValidNumberOfKeys(0));
        assertTrue(HolderType.LEGAL_PERSON.isValidNumberOfKeys(10));
        assertTrue(HolderType.LEGAL_PERSON.isValidNumberOfKeys(20));
    }

    @Test
    public void testInvalidNumberOfKeysForLegalPerson() {
        assertFalse(HolderType.LEGAL_PERSON.isValidNumberOfKeys(-1));
        assertFalse(HolderType.LEGAL_PERSON.isValidNumberOfKeys(21));
    }

    @Test
    public void testBoundaryValuesForNaturalPerson() {
        assertTrue(HolderType.NATURAL_PERSON.isValidNumberOfKeys(0));
        assertTrue(HolderType.NATURAL_PERSON.isValidNumberOfKeys(5));
    }

    @Test
    public void testBoundaryValuesForLegalPerson() {
        assertTrue(HolderType.LEGAL_PERSON.isValidNumberOfKeys(0));
        assertTrue(HolderType.LEGAL_PERSON.isValidNumberOfKeys(20));
    }
}
