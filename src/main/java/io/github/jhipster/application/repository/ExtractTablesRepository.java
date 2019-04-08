package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ExtractTables;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ExtractTables entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExtractTablesRepository extends JpaRepository<ExtractTables, Long> {

}
