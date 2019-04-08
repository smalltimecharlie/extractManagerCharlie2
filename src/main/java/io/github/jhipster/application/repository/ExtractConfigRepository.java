package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ExtractConfig;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ExtractConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExtractConfigRepository extends JpaRepository<ExtractConfig, Long> {

}
