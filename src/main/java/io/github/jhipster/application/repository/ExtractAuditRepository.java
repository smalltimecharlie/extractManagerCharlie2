package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ExtractAudit;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ExtractAudit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExtractAuditRepository extends JpaRepository<ExtractAudit, Long> {

}
