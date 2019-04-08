package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.domain.ExtractConfig;
import io.github.jhipster.application.repository.ExtractConfigRepository;
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
 * REST controller for managing ExtractConfig.
 */
@RestController
@RequestMapping("/api")
public class ExtractConfigResource {

    private final Logger log = LoggerFactory.getLogger(ExtractConfigResource.class);

    private static final String ENTITY_NAME = "extractConfig";

    private final ExtractConfigRepository extractConfigRepository;

    public ExtractConfigResource(ExtractConfigRepository extractConfigRepository) {
        this.extractConfigRepository = extractConfigRepository;
    }

    /**
     * POST  /extract-configs : Create a new extractConfig.
     *
     * @param extractConfig the extractConfig to create
     * @return the ResponseEntity with status 201 (Created) and with body the new extractConfig, or with status 400 (Bad Request) if the extractConfig has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/extract-configs")
    public ResponseEntity<ExtractConfig> createExtractConfig(@RequestBody ExtractConfig extractConfig) throws URISyntaxException {
        log.debug("REST request to save ExtractConfig : {}", extractConfig);
        if (extractConfig.getId() != null) {
            throw new BadRequestAlertException("A new extractConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExtractConfig result = extractConfigRepository.save(extractConfig);
        return ResponseEntity.created(new URI("/api/extract-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /extract-configs : Updates an existing extractConfig.
     *
     * @param extractConfig the extractConfig to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated extractConfig,
     * or with status 400 (Bad Request) if the extractConfig is not valid,
     * or with status 500 (Internal Server Error) if the extractConfig couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/extract-configs")
    public ResponseEntity<ExtractConfig> updateExtractConfig(@RequestBody ExtractConfig extractConfig) throws URISyntaxException {
        log.debug("REST request to update ExtractConfig : {}", extractConfig);
        if (extractConfig.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExtractConfig result = extractConfigRepository.save(extractConfig);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, extractConfig.getId().toString()))
            .body(result);
    }

    /**
     * GET  /extract-configs : get all the extractConfigs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of extractConfigs in body
     */
    @GetMapping("/extract-configs")
    public List<ExtractConfig> getAllExtractConfigs() {
        log.debug("REST request to get all ExtractConfigs");
        return extractConfigRepository.findAll();
    }

    /**
     * GET  /extract-configs/:id : get the "id" extractConfig.
     *
     * @param id the id of the extractConfig to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the extractConfig, or with status 404 (Not Found)
     */
    @GetMapping("/extract-configs/{id}")
    public ResponseEntity<ExtractConfig> getExtractConfig(@PathVariable Long id) {
        log.debug("REST request to get ExtractConfig : {}", id);
        Optional<ExtractConfig> extractConfig = extractConfigRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(extractConfig);
    }

    /**
     * DELETE  /extract-configs/:id : delete the "id" extractConfig.
     *
     * @param id the id of the extractConfig to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/extract-configs/{id}")
    public ResponseEntity<Void> deleteExtractConfig(@PathVariable Long id) {
        log.debug("REST request to delete ExtractConfig : {}", id);
        extractConfigRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
