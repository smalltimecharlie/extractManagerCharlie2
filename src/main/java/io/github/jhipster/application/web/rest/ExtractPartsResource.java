package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.domain.ExtractParts;
import io.github.jhipster.application.repository.ExtractPartsRepository;
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
 * REST controller for managing ExtractParts.
 */
@RestController
@RequestMapping("/api")
public class ExtractPartsResource {

    private final Logger log = LoggerFactory.getLogger(ExtractPartsResource.class);

    private static final String ENTITY_NAME = "extractParts";

    private final ExtractPartsRepository extractPartsRepository;

    public ExtractPartsResource(ExtractPartsRepository extractPartsRepository) {
        this.extractPartsRepository = extractPartsRepository;
    }

    /**
     * POST  /extract-parts : Create a new extractParts.
     *
     * @param extractParts the extractParts to create
     * @return the ResponseEntity with status 201 (Created) and with body the new extractParts, or with status 400 (Bad Request) if the extractParts has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/extract-parts")
    public ResponseEntity<ExtractParts> createExtractParts(@RequestBody ExtractParts extractParts) throws URISyntaxException {
        log.debug("REST request to save ExtractParts : {}", extractParts);
        if (extractParts.getId() != null) {
            throw new BadRequestAlertException("A new extractParts cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExtractParts result = extractPartsRepository.save(extractParts);
        return ResponseEntity.created(new URI("/api/extract-parts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /extract-parts : Updates an existing extractParts.
     *
     * @param extractParts the extractParts to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated extractParts,
     * or with status 400 (Bad Request) if the extractParts is not valid,
     * or with status 500 (Internal Server Error) if the extractParts couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/extract-parts")
    public ResponseEntity<ExtractParts> updateExtractParts(@RequestBody ExtractParts extractParts) throws URISyntaxException {
        log.debug("REST request to update ExtractParts : {}", extractParts);
        if (extractParts.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExtractParts result = extractPartsRepository.save(extractParts);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, extractParts.getId().toString()))
            .body(result);
    }

    /**
     * GET  /extract-parts : get all the extractParts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of extractParts in body
     */
    @GetMapping("/extract-parts")
    public List<ExtractParts> getAllExtractParts() {
        log.debug("REST request to get all ExtractParts");
        return extractPartsRepository.findAll();
    }

    /**
     * GET  /extract-parts/:id : get the "id" extractParts.
     *
     * @param id the id of the extractParts to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the extractParts, or with status 404 (Not Found)
     */
    @GetMapping("/extract-parts/{id}")
    public ResponseEntity<ExtractParts> getExtractParts(@PathVariable Long id) {
        log.debug("REST request to get ExtractParts : {}", id);
        Optional<ExtractParts> extractParts = extractPartsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(extractParts);
    }

    /**
     * DELETE  /extract-parts/:id : delete the "id" extractParts.
     *
     * @param id the id of the extractParts to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/extract-parts/{id}")
    public ResponseEntity<Void> deleteExtractParts(@PathVariable Long id) {
        log.debug("REST request to delete ExtractParts : {}", id);
        extractPartsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
