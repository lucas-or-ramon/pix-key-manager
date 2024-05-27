package dev.canoa.pixkeymanager.infrastructure.databases.postgresql;

import dev.canoa.pixkeymanager.infrastructure.databases.postgresql.entity.PixKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPixKeyRepository extends JpaRepository<PixKeyEntity, String>, JpaSpecificationExecutor<PixKeyEntity> {
}
