package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ExtractOrganisationAudit;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ExtractOrganisationAudit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExtractOrganisationAuditRepository extends JpaRepository<ExtractOrganisationAudit, Long> {

}
