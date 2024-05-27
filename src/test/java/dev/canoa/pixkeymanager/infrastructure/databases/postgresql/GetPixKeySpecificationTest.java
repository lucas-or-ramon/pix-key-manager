package dev.canoa.pixkeymanager.infrastructure.databases.postgresql;

import dev.canoa.pixkeymanager.domain.model.GetPixKey;
import dev.canoa.pixkeymanager.domain.model.KeyType;
import dev.canoa.pixkeymanager.infrastructure.databases.postgresql.entity.PixKeyEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetPixKeySpecificationTest {

    @Test
    public void testWithNoParams() {
        GetPixKey params = GetPixKey.builder().build();

        Root<PixKeyEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);

        Specification<PixKeyEntity> specification = GetPixKeySpecification.with(params);
        Predicate predicate = specification.toPredicate(root, query, criteriaBuilder);

        assertNull(predicate);
    }

    @Test
    public void testWithKeyType() {
        GetPixKey params = GetPixKey.builder().keyType(KeyType.CPF).build();

        Root<PixKeyEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate expectedPredicate = mock(Predicate.class);
        when(criteriaBuilder.equal(root.get("keyType"), KeyType.CPF.getType())).thenReturn(expectedPredicate);

        Specification<PixKeyEntity> specification = GetPixKeySpecification.with(params);
        Predicate predicate = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(predicate);
        assertEquals(expectedPredicate, predicate);
    }

    @Test
    public void testWithBranchAndAccountNumber() {
        GetPixKey params = GetPixKey.builder().branchNumber(1234).accountNumber(5678).build();

        Root<PixKeyEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate expectedPredicate1 = mock(Predicate.class);
        Predicate expectedPredicate2 = mock(Predicate.class);
        when(criteriaBuilder.equal(root.get("branchNumber"), 1234)).thenReturn(expectedPredicate1);
        when(criteriaBuilder.equal(root.get("accountNumber"), 5678)).thenReturn(expectedPredicate2);

        Specification<PixKeyEntity> specification = GetPixKeySpecification.with(params);
        Predicate predicate = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(predicate);
        assertTrue(predicate.toString().contains("branchNumber"));
        assertTrue(predicate.toString().contains("accountNumber"));
    }

    @Test
    public void testWithAccountHolderName() {
        GetPixKey params = GetPixKey.builder().accountHolderName("John Doe").build();

        Root<PixKeyEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate expectedPredicate = mock(Predicate.class);
        when(criteriaBuilder.equal(root.get("accountHolderName"), "John Doe")).thenReturn(expectedPredicate);

        Specification<PixKeyEntity> specification = GetPixKeySpecification.with(params);
        Predicate predicate = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(predicate);
        assertEquals(expectedPredicate, predicate);
    }

    @Test
    public void testWithInclusionDateTime() {
        GetPixKey params = GetPixKey.builder().inclusionDateTime("2022-05-30T12:00:00").build();

        Root<PixKeyEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate expectedPredicate = mock(Predicate.class);
        when(criteriaBuilder.equal(root.get("inclusionDateTime"), "2022-05-30T12:00:00")).thenReturn(expectedPredicate);

        Specification<PixKeyEntity> specification = GetPixKeySpecification.with(params);
        Predicate predicate = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(predicate);
        assertEquals(expectedPredicate, predicate);
    }

    @Test
    public void testWithDeactivationDateTime() {
        GetPixKey params = GetPixKey.builder().deactivationDateTime("2022-06-30T12:00:00").build();

        Root<PixKeyEntity> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate expectedPredicate = mock(Predicate.class);
        when(criteriaBuilder.equal(root.get("deactivationDateTime"), "2022-06-30T12:00:00")).thenReturn(expectedPredicate);

        Specification<PixKeyEntity> specification = GetPixKeySpecification.with(params);
        Predicate predicate = specification.toPredicate(root, query, criteriaBuilder);

        assertNotNull(predicate);
        assertEquals(expectedPredicate, predicate);
    }
}