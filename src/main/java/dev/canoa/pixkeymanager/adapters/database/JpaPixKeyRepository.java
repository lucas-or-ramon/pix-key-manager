package dev.canoa.pixkeymanager.adapters.database;

import dev.canoa.pixkeymanager.adapters.database.entity.PixKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPixKeyRepository extends JpaRepository<PixKeyEntity, String>, JpaSpecificationExecutor<PixKeyEntity> {
}
