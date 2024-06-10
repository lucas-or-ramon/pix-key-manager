package dev.canoa.pixkeymanager.adapters.database;

import dev.canoa.pixkeymanager.adapters.database.entity.PixKeyEntity;
import dev.canoa.pixkeymanager.application.GetPixKey;
import dev.canoa.pixkeymanager.application.PixKey;
import dev.canoa.pixkeymanager.application.exception.RepositoryException;
import dev.canoa.pixkeymanager.application.PixKeyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PixPixKeyAdapter implements PixKeyRepository {

    private final JpaPixKeyRepository jpaPixKeyRepository;

    @Override
    public Optional<PixKey> findById(String id) {
        try {
            return jpaPixKeyRepository.findById(id).map(PixKeyEntity::toModel);
        } catch (Exception e) {
            throw new RepositoryException("Erro ao buscar chave Pix");
        }
    }

    @Override
    public List<PixKey> findWith(GetPixKey params) {
        Specification<PixKeyEntity> specification = GetPixKeySpecification.with(params);
        try {
            return jpaPixKeyRepository.findAll(specification)
                    .stream()
                    .map(PixKeyEntity::toModel)
                    .toList();
        } catch (Exception e) {
            throw new RepositoryException("Erro ao buscar chaves Pix");
        }
    }

//    @Override
//    public long count(Integer branchNumber, Integer accountNumber) {
//        return jpaPixKeyRepository.count((root, query, criteriaBuilder) -> criteriaBuilder.and(
//                criteriaBuilder.equal(root.get("branchNumber"), branchNumber),
//                criteriaBuilder.equal(root.get("accountNumber"), accountNumber),
//                criteriaBuilder.isNull(root.get("deactivationDateTime"))
//        ));
//    }

    @Override
    public PixKey create(PixKey pixKey) {
        PixKeyEntity entity = PixKeyEntity.fromModel(pixKey);
        try {
            PixKeyEntity pixKeySaved = jpaPixKeyRepository.save(entity);
            return pixKeySaved.toModel();
        } catch (Exception e) {
            throw new RepositoryException("Erro ao salvar chave Pix");
        }
    }

//    @Override
//    public PixKey findByKeyValue(String key) {
//        return jpaPixKeyRepository.findOne(
//                        (root, query, criteriaBuilder) -> criteriaBuilder.and(
//                                criteriaBuilder.equal(root.get("keyValue"), key),
//                                criteriaBuilder.isNull(root.get("deactivationDateTime"))
//                        )
//                )
//                .map(PixKeyEntity::toModel)
//                .orElse(null);
//    }

    @Override
    public PixKey update(PixKey pixKey) {
        PixKeyEntity entity = PixKeyEntity.fromModel(pixKey);
        try {
            PixKeyEntity pixKeySaved = jpaPixKeyRepository.save(entity);
            return pixKeySaved.toModel();
        } catch (Exception e) {
            throw new RepositoryException("Erro ao atualizar chave Pix");
        }
    }

    @Override
    public long count(String accountId) {
        return 0;
    }
}
