package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ExtractDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ExtractDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExtractDetailsRepository extends JpaRepository<ExtractDetails, Long> {

}
