package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.domain.ExtractOrganisationAudit;
import io.github.jhipster.application.repository.ExtractOrganisationAuditRepository;
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
 * REST controller for managing ExtractOrganisationAudit.
 */
@RestController
@RequestMapping("/api")
public class ExtractOrganisationAuditResource {

    private final Logger log = LoggerFactory.getLogger(ExtractOrganisationAuditResource.class);

    private static final String ENTITY_NAME = "extractOrganisationAudit";

    private final ExtractOrganisationAuditRepository extractOrganisationAuditRepository;

    public ExtractOrganisationAuditResource(ExtractOrganisationAuditRepository extractOrganisationAuditRepository) {
        this.extractOrganisationAuditRepository = extractOrganisationAuditRepository;
    }

    /**
     * POST  /extract-organisation-audits : Create a new extractOrganisationAudit.
     *
     * @param extractOrganisationAudit the extractOrganisationAudit to create
     * @return the ResponseEntity with status 201 (Created) and with body the new extractOrganisationAudit, or with status 400 (Bad Request) if the extractOrganisationAudit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/extract-organisation-audits")
    public ResponseEntity<ExtractOrganisationAudit> createExtractOrganisationAudit(@RequestBody ExtractOrganisationAudit extractOrganisationAudit) throws URISyntaxException {
        log.debug("REST request to save ExtractOrganisationAudit : {}", extractOrganisationAudit);
        if (extractOrganisationAudit.getId() != null) {
            throw new BadRequestAlertException("A new extractOrganisationAudit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExtractOrganisationAudit result = extractOrganisationAuditRepository.save(extractOrganisationAudit);
        return ResponseEntity.created(new URI("/api/extract-organisation-audits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /extract-organisation-audits : Updates an existing extractOrganisationAudit.
     *
     * @param extractOrganisationAudit the extractOrganisationAudit to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated extractOrganisationAudit,
     * or with status 400 (Bad Request) if the extractOrganisationAudit is not valid,
     * or with status 500 (Internal Server Error) if the extractOrganisationAudit couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/extract-organisation-audits")
    public ResponseEntity<ExtractOrganisationAudit> updateExtractOrganisationAudit(@RequestBody ExtractOrganisationAudit extractOrganisationAudit) throws URISyntaxException {
        log.debug("REST request to update ExtractOrganisationAudit : {}", extractOrganisationAudit);
        if (extractOrganisationAudit.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExtractOrganisationAudit result = extractOrganisationAuditRepository.save(extractOrganisationAudit);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, extractOrganisationAudit.getId().toString()))
            .body(result);
    }

    /**
     * GET  /extract-organisation-audits : get all the extractOrganisationAudits.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of extractOrganisationAudits in body
     */
    @GetMapping("/extract-organisation-audits")
    public List<ExtractOrganisationAudit> getAllExtractOrganisationAudits() {
        log.debug("REST request to get all ExtractOrganisationAudits");
        return extractOrganisationAuditRepository.findAll();
    }

    /**
     * GET  /extract-organisation-audits/:id : get the "id" extractOrganisationAudit.
     *
     * @param id the id of the extractOrganisationAudit to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the extractOrganisationAudit, or with status 404 (Not Found)
     */
    @GetMapping("/extract-organisation-audits/{id}")
    public ResponseEntity<ExtractOrganisationAudit> getExtractOrganisationAudit(@PathVariable Long id) {
        log.debug("REST request to get ExtractOrganisationAudit : {}", id);
        Optional<ExtractOrganisationAudit> extractOrganisationAudit = extractOrganisationAuditRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(extractOrganisationAudit);
    }

    /**
     * DELETE  /extract-organisation-audits/:id : delete the "id" extractOrganisationAudit.
     *
     * @param id the id of the extractOrganisationAudit to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/extract-organisation-audits/{id}")
    public ResponseEntity<Void> deleteExtractOrganisationAudit(@PathVariable Long id) {
        log.debug("REST request to delete ExtractOrganisationAudit : {}", id);
        extractOrganisationAuditRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
