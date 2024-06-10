package dev.canoa.pixkeymanager.adapters.database;

import dev.canoa.pixkeymanager.adapters.database.entity.PixKeyEntity;
import dev.canoa.pixkeymanager.application.GetPixKey;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public final class GetPixKeySpecification {
    public static Specification<PixKeyEntity> with(GetPixKey params) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (params.keyType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("keyType"), params.keyType().name()));
            }
            if (params.branchNumber() != null && params.accountNumber() != null) {
                predicates.add(criteriaBuilder.equal(root.get("branchNumber"), params.branchNumber()));
                predicates.add(criteriaBuilder.equal(root.get("accountNumber"), params.accountNumber()));
            }
            if (params.accountHolderName() != null && !params.accountHolderName().trim().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("accountHolderName"), params.accountHolderName()));
            }
            if (params.inclusionDateTime() != null) {
                predicates.add(criteriaBuilder.between(root.get("inclusionDateTime"), params.inclusionDateTime().atStartOfDay(), params.inclusionDateTime().plusDays(1).atStartOfDay()));
            }
            if (params.deactivationDateTime() != null) {
                predicates.add(criteriaBuilder.between(root.get("deactivationDateTime"), params.deactivationDateTime().atStartOfDay(), params.deactivationDateTime().plusDays(1).atStartOfDay()));
            } else {
                predicates.add(criteriaBuilder.isNull(root.get("deactivationDateTime")));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
