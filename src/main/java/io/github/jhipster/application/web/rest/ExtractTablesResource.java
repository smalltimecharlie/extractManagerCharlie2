package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.domain.ExtractTables;
import io.github.jhipster.application.repository.ExtractTablesRepository;
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
 * REST controller for managing ExtractTables.
 */
@RestController
@RequestMapping("/api")
public class ExtractTablesResource {

    private final Logger log = LoggerFactory.getLogger(ExtractTablesResource.class);

    private static final String ENTITY_NAME = "extractTables";

    private final ExtractTablesRepository extractTablesRepository;

    public ExtractTablesResource(ExtractTablesRepository extractTablesRepository) {
        this.extractTablesRepository = extractTablesRepository;
    }

    /**
     * POST  /extract-tables : Create a new extractTables.
     *
     * @param extractTables the extractTables to create
     * @return the ResponseEntity with status 201 (Created) and with body the new extractTables, or with status 400 (Bad Request) if the extractTables has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/extract-tables")
    public ResponseEntity<ExtractTables> createExtractTables(@RequestBody ExtractTables extractTables) throws URISyntaxException {
        log.debug("REST request to save ExtractTables : {}", extractTables);
        if (extractTables.getId() != null) {
            throw new BadRequestAlertException("A new extractTables cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExtractTables result = extractTablesRepository.save(extractTables);
        return ResponseEntity.created(new URI("/api/extract-tables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /extract-tables : Updates an existing extractTables.
     *
     * @param extractTables the extractTables to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated extractTables,
     * or with status 400 (Bad Request) if the extractTables is not valid,
     * or with status 500 (Internal Server Error) if the extractTables couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/extract-tables")
    public ResponseEntity<ExtractTables> updateExtractTables(@RequestBody ExtractTables extractTables) throws URISyntaxException {
        log.debug("REST request to update ExtractTables : {}", extractTables);
        if (extractTables.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExtractTables result = extractTablesRepository.save(extractTables);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, extractTables.getId().toString()))
            .body(result);
    }

    /**
     * GET  /extract-tables : get all the extractTables.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of extractTables in body
     */
    @GetMapping("/extract-tables")
    public List<ExtractTables> getAllExtractTables() {
        log.debug("REST request to get all ExtractTables");
        return extractTablesRepository.findAll();
    }

    /**
     * GET  /extract-tables/:id : get the "id" extractTables.
     *
     * @param id the id of the extractTables to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the extractTables, or with status 404 (Not Found)
     */
    @GetMapping("/extract-tables/{id}")
    public ResponseEntity<ExtractTables> getExtractTables(@PathVariable Long id) {
        log.debug("REST request to get ExtractTables : {}", id);
        Optional<ExtractTables> extractTables = extractTablesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(extractTables);
    }

    /**
     * DELETE  /extract-tables/:id : delete the "id" extractTables.
     *
     * @param id the id of the extractTables to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/extract-tables/{id}")
    public ResponseEntity<Void> deleteExtractTables(@PathVariable Long id) {
        log.debug("REST request to delete ExtractTables : {}", id);
        extractTablesRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
