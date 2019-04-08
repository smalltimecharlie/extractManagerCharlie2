package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.domain.ExtractDetails;
import io.github.jhipster.application.repository.ExtractDetailsRepository;
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
 * REST controller for managing ExtractDetails.
 */
@RestController
@RequestMapping("/api")
public class ExtractDetailsResource {

    private final Logger log = LoggerFactory.getLogger(ExtractDetailsResource.class);

    private static final String ENTITY_NAME = "extractDetails";

    private final ExtractDetailsRepository extractDetailsRepository;

    public ExtractDetailsResource(ExtractDetailsRepository extractDetailsRepository) {
        this.extractDetailsRepository = extractDetailsRepository;
    }

    /**
     * POST  /extract-details : Create a new extractDetails.
     *
     * @param extractDetails the extractDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new extractDetails, or with status 400 (Bad Request) if the extractDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/extract-details")
    public ResponseEntity<ExtractDetails> createExtractDetails(@RequestBody ExtractDetails extractDetails) throws URISyntaxException {
        log.debug("REST request to save ExtractDetails : {}", extractDetails);
        if (extractDetails.getId() != null) {
            throw new BadRequestAlertException("A new extractDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExtractDetails result = extractDetailsRepository.save(extractDetails);
        return ResponseEntity.created(new URI("/api/extract-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /extract-details : Updates an existing extractDetails.
     *
     * @param extractDetails the extractDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated extractDetails,
     * or with status 400 (Bad Request) if the extractDetails is not valid,
     * or with status 500 (Internal Server Error) if the extractDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/extract-details")
    public ResponseEntity<ExtractDetails> updateExtractDetails(@RequestBody ExtractDetails extractDetails) throws URISyntaxException {
        log.debug("REST request to update ExtractDetails : {}", extractDetails);
        if (extractDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExtractDetails result = extractDetailsRepository.save(extractDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, extractDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /extract-details : get all the extractDetails.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of extractDetails in body
     */
    @GetMapping("/extract-details")
    public List<ExtractDetails> getAllExtractDetails() {
        log.debug("REST request to get all ExtractDetails");
        return extractDetailsRepository.findAll();
    }

    /**
     * GET  /extract-details/:id : get the "id" extractDetails.
     *
     * @param id the id of the extractDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the extractDetails, or with status 404 (Not Found)
     */
    @GetMapping("/extract-details/{id}")
    public ResponseEntity<ExtractDetails> getExtractDetails(@PathVariable Long id) {
        log.debug("REST request to get ExtractDetails : {}", id);
        Optional<ExtractDetails> extractDetails = extractDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(extractDetails);
    }

    /**
     * DELETE  /extract-details/:id : delete the "id" extractDetails.
     *
     * @param id the id of the extractDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/extract-details/{id}")
    public ResponseEntity<Void> deleteExtractDetails(@PathVariable Long id) {
        log.debug("REST request to delete ExtractDetails : {}", id);
        extractDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
