package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ExtractParts;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ExtractParts entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExtractPartsRepository extends JpaRepository<ExtractParts, Long> {

}
