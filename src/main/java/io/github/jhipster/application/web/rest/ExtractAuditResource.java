package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.domain.ExtractAudit;
import io.github.jhipster.application.repository.ExtractAuditRepository;
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
 * REST controller for managing ExtractAudit.
 */
@RestController
@RequestMapping("/api")
public class ExtractAuditResource {

    private final Logger log = LoggerFactory.getLogger(ExtractAuditResource.class);

    private static final String ENTITY_NAME = "extractAudit";

    private final ExtractAuditRepository extractAuditRepository;

    public ExtractAuditResource(ExtractAuditRepository extractAuditRepository) {
        this.extractAuditRepository = extractAuditRepository;
    }

    /**
     * POST  /extract-audits : Create a new extractAudit.
     *
     * @param extractAudit the extractAudit to create
     * @return the ResponseEntity with status 201 (Created) and with body the new extractAudit, or with status 400 (Bad Request) if the extractAudit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/extract-audits")
    public ResponseEntity<ExtractAudit> createExtractAudit(@RequestBody ExtractAudit extractAudit) throws URISyntaxException {
        log.debug("REST request to save ExtractAudit : {}", extractAudit);
        if (extractAudit.getId() != null) {
            throw new BadRequestAlertException("A new extractAudit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExtractAudit result = extractAuditRepository.save(extractAudit);
        return ResponseEntity.created(new URI("/api/extract-audits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /extract-audits : Updates an existing extractAudit.
     *
     * @param extractAudit the extractAudit to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated extractAudit,
     * or with status 400 (Bad Request) if the extractAudit is not valid,
     * or with status 500 (Internal Server Error) if the extractAudit couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/extract-audits")
    public ResponseEntity<ExtractAudit> updateExtractAudit(@RequestBody ExtractAudit extractAudit) throws URISyntaxException {
        log.debug("REST request to update ExtractAudit : {}", extractAudit);
        if (extractAudit.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExtractAudit result = extractAuditRepository.save(extractAudit);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, extractAudit.getId().toString()))
            .body(result);
    }

    /**
     * GET  /extract-audits : get all the extractAudits.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of extractAudits in body
     */
    @GetMapping("/extract-audits")
    public List<ExtractAudit> getAllExtractAudits() {
        log.debug("REST request to get all ExtractAudits");
        return extractAuditRepository.findAll();
    }

    /**
     * GET  /extract-audits/:id : get the "id" extractAudit.
     *
     * @param id the id of the extractAudit to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the extractAudit, or with status 404 (Not Found)
     */
    @GetMapping("/extract-audits/{id}")
    public ResponseEntity<ExtractAudit> getExtractAudit(@PathVariable Long id) {
        log.debug("REST request to get ExtractAudit : {}", id);
        Optional<ExtractAudit> extractAudit = extractAuditRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(extractAudit);
    }

    /**
     * DELETE  /extract-audits/:id : delete the "id" extractAudit.
     *
     * @param id the id of the extractAudit to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/extract-audits/{id}")
    public ResponseEntity<Void> deleteExtractAudit(@PathVariable Long id) {
        log.debug("REST request to delete ExtractAudit : {}", id);
        extractAuditRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
