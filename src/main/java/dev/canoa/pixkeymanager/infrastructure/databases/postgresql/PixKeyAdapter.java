package dev.canoa.pixkeymanager.infrastructure.databases.postgresql;

import dev.canoa.pixkeymanager.domain.model.GetPixKey;
import dev.canoa.pixkeymanager.domain.model.KeyType;
import dev.canoa.pixkeymanager.domain.model.PixKey;
import dev.canoa.pixkeymanager.domain.model.exception.NotFoundPixKeyException;
import dev.canoa.pixkeymanager.domain.ports.outbound.PixKeyRepository;
import dev.canoa.pixkeymanager.infrastructure.databases.postgresql.entity.PixKeyEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PixKeyAdapter implements PixKeyRepository {

    private final JpaPixKeyRepository jpaPixKeyRepository;

    @Override
    public PixKey findById(String id) {
        return jpaPixKeyRepository.findById(id)
                .map(PixKeyEntity::toModel)
                .orElseThrow(() -> new NotFoundPixKeyException("Chave Pix não encontrada"));
    }

    @Override
    public List<PixKey> findWith(GetPixKey params) {
        Specification<PixKeyEntity> specification = GetPixKeySpecification.with(params);
        return jpaPixKeyRepository.findAll(specification)
                .stream()
                .map(PixKeyEntity::toModel)
                .toList();
    }

    @Override
    public long count(Integer branchNumber, Integer accountNumber) {
        return jpaPixKeyRepository.count((root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("branchNumber"), branchNumber),
                criteriaBuilder.equal(root.get("accountNumber"), accountNumber)
        ));
    }

    @Override
    public String create(PixKey pixKey) {
        PixKeyEntity entity = PixKeyEntity.fromModel(pixKey);
        PixKeyEntity pixKeySaved = jpaPixKeyRepository.save(entity);
        return pixKeySaved.getId();
    }

    @Override
    public PixKey findByKeyValue(String key) {
        return jpaPixKeyRepository.findOne(
                        (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("keyValue"), key)
                )
                .map(PixKeyEntity::toModel)
                .orElse(null);
    }

    @Override
    public boolean existsId(String id) {
        return jpaPixKeyRepository.existsById(id);
    }

    @Override
    public PixKey update(PixKey pixKey) {
        PixKeyEntity entity = PixKeyEntity.fromModel(pixKey);
        PixKeyEntity pixKeySaved = jpaPixKeyRepository.save(entity);
        return pixKeySaved.toModel();
    }
}
