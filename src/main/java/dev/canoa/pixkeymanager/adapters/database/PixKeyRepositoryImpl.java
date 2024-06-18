package dev.canoa.pixkeymanager.adapters.database;

import dev.canoa.pixkeymanager.adapters.database.entity.PixKeyEntity;
import dev.canoa.pixkeymanager.application.GetPixKey;
import dev.canoa.pixkeymanager.application.PixKey;
import dev.canoa.pixkeymanager.application.exception.RepositoryException;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PixKeyRepositoryImpl implements dev.canoa.pixkeymanager.application.PixKeyRepository {

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
