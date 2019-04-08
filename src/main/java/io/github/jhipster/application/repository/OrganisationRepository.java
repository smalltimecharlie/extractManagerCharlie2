package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Organisation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Organisation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

}
