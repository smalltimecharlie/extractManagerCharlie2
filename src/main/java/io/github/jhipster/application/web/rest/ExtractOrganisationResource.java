package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.domain.ExtractOrganisation;
import io.github.jhipster.application.repository.ExtractOrganisationRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ExtractOrganisation.
 */
@RestController
@RequestMapping("/api")
public class ExtractOrganisationResource {

    private final Logger log = LoggerFactory.getLogger(ExtractOrganisationResource.class);

    private static final String ENTITY_NAME = "extractOrganisation";

    private final ExtractOrganisationRepository extractOrganisationRepository;

    public ExtractOrganisationResource(ExtractOrganisationRepository extractOrganisationRepository) {
        this.extractOrganisationRepository = extractOrganisationRepository;
    }

    /**
     * POST  /extract-organisations : Create a new extractOrganisation.
     *
     * @param extractOrganisation the extractOrganisation to create
     * @return the ResponseEntity with status 201 (Created) and with body the new extractOrganisation, or with status 400 (Bad Request) if the extractOrganisation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/extract-organisations")
    public ResponseEntity<ExtractOrganisation> createExtractOrganisation(@RequestBody ExtractOrganisation extractOrganisation) throws URISyntaxException {
        log.debug("REST request to save ExtractOrganisation : {}", extractOrganisation);
        if (extractOrganisation.getId() != null) {
            throw new BadRequestAlertException("A new extractOrganisation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExtractOrganisation result = extractOrganisationRepository.save(extractOrganisation);
        return ResponseEntity.created(new URI("/api/extract-organisations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /extract-organisations : Updates an existing extractOrganisation.
     *
     * @param extractOrganisation the extractOrganisation to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated extractOrganisation,
     * or with status 400 (Bad Request) if the extractOrganisation is not valid,
     * or with status 500 (Internal Server Error) if the extractOrganisation couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/extract-organisations")
    public ResponseEntity<ExtractOrganisation> updateExtractOrganisation(@RequestBody ExtractOrganisation extractOrganisation) throws URISyntaxException {
        log.debug("REST request to update ExtractOrganisation : {}", extractOrganisation);
        if (extractOrganisation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExtractOrganisation result = extractOrganisationRepository.save(extractOrganisation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, extractOrganisation.getId().toString()))
            .body(result);
    }

    /**
     * GET  /extract-organisations : get all the extractOrganisations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of extractOrganisations in body
     */
    @GetMapping("/extract-organisations")
    public List<ExtractOrganisation> getAllExtractOrganisations() {
        log.debug("REST request to get all ExtractOrganisations");
        return extractOrganisationRepository.findAll();
    }

    /**
     * GET  /extract-organisations/:id : get the "id" extractOrganisation.
     *
     * @param id the id of the extractOrganisation to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the extractOrganisation, or with status 404 (Not Found)
     */
    @GetMapping("/extract-organisations/{id}")
    public ResponseEntity<ExtractOrganisation> getExtractOrganisation(@PathVariable Long id) {
        log.debug("REST request to get ExtractOrganisation : {}", id);
        Optional<ExtractOrganisation> extractOrganisation = extractOrganisationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(extractOrganisation);
    }

    /**
     * DELETE  /extract-organisations/:id : delete the "id" extractOrganisation.
     *
     * @param id the id of the extractOrganisation to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/extract-organisations/{id}")
    public ResponseEntity<Void> deleteExtractOrganisation(@PathVariable Long id) {
        log.debug("REST request to delete ExtractOrganisation : {}", id);
        extractOrganisationRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
