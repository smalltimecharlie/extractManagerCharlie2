package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.ExtractManagerCharlie2App;

import io.github.jhipster.application.domain.ExtractOrganisationAudit;
import io.github.jhipster.application.repository.ExtractOrganisationAuditRepository;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ExtractOrganisationAuditResource REST controller.
 *
 * @see ExtractOrganisationAuditResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExtractManagerCharlie2App.class)
public class ExtractOrganisationAuditResourceIntTest {

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_SUCCESS = false;
    private static final Boolean UPDATED_SUCCESS = true;

    @Autowired
    private ExtractOrganisationAuditRepository extractOrganisationAuditRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restExtractOrganisationAuditMockMvc;

    private ExtractOrganisationAudit extractOrganisationAudit;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExtractOrganisationAuditResource extractOrganisationAuditResource = new ExtractOrganisationAuditResource(extractOrganisationAuditRepository);
        this.restExtractOrganisationAuditMockMvc = MockMvcBuilders.standaloneSetup(extractOrganisationAuditResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExtractOrganisationAudit createEntity(EntityManager em) {
        ExtractOrganisationAudit extractOrganisationAudit = new ExtractOrganisationAudit()
            .createdDate(DEFAULT_CREATED_DATE)
            .success(DEFAULT_SUCCESS);
        return extractOrganisationAudit;
    }

    @Before
    public void initTest() {
        extractOrganisationAudit = createEntity(em);
    }

    @Test
    @Transactional
    public void createExtractOrganisationAudit() throws Exception {
        int databaseSizeBeforeCreate = extractOrganisationAuditRepository.findAll().size();

        // Create the ExtractOrganisationAudit
        restExtractOrganisationAuditMockMvc.perform(post("/api/extract-organisation-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extractOrganisationAudit)))
            .andExpect(status().isCreated());

        // Validate the ExtractOrganisationAudit in the database
        List<ExtractOrganisationAudit> extractOrganisationAuditList = extractOrganisationAuditRepository.findAll();
        assertThat(extractOrganisationAuditList).hasSize(databaseSizeBeforeCreate + 1);
        ExtractOrganisationAudit testExtractOrganisationAudit = extractOrganisationAuditList.get(extractOrganisationAuditList.size() - 1);
        assertThat(testExtractOrganisationAudit.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testExtractOrganisationAudit.isSuccess()).isEqualTo(DEFAULT_SUCCESS);
    }

    @Test
    @Transactional
    public void createExtractOrganisationAuditWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = extractOrganisationAuditRepository.findAll().size();

        // Create the ExtractOrganisationAudit with an existing ID
        extractOrganisationAudit.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExtractOrganisationAuditMockMvc.perform(post("/api/extract-organisation-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extractOrganisationAudit)))
            .andExpect(status().isBadRequest());

        // Validate the ExtractOrganisationAudit in the database
        List<ExtractOrganisationAudit> extractOrganisationAuditList = extractOrganisationAuditRepository.findAll();
        assertThat(extractOrganisationAuditList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllExtractOrganisationAudits() throws Exception {
        // Initialize the database
        extractOrganisationAuditRepository.saveAndFlush(extractOrganisationAudit);

        // Get all the extractOrganisationAuditList
        restExtractOrganisationAuditMockMvc.perform(get("/api/extract-organisation-audits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(extractOrganisationAudit.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].success").value(hasItem(DEFAULT_SUCCESS.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getExtractOrganisationAudit() throws Exception {
        // Initialize the database
        extractOrganisationAuditRepository.saveAndFlush(extractOrganisationAudit);

        // Get the extractOrganisationAudit
        restExtractOrganisationAuditMockMvc.perform(get("/api/extract-organisation-audits/{id}", extractOrganisationAudit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(extractOrganisationAudit.getId().intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.success").value(DEFAULT_SUCCESS.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingExtractOrganisationAudit() throws Exception {
        // Get the extractOrganisationAudit
        restExtractOrganisationAuditMockMvc.perform(get("/api/extract-organisation-audits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExtractOrganisationAudit() throws Exception {
        // Initialize the database
        extractOrganisationAuditRepository.saveAndFlush(extractOrganisationAudit);

        int databaseSizeBeforeUpdate = extractOrganisationAuditRepository.findAll().size();

        // Update the extractOrganisationAudit
        ExtractOrganisationAudit updatedExtractOrganisationAudit = extractOrganisationAuditRepository.findById(extractOrganisationAudit.getId()).get();
        // Disconnect from session so that the updates on updatedExtractOrganisationAudit are not directly saved in db
        em.detach(updatedExtractOrganisationAudit);
        updatedExtractOrganisationAudit
            .createdDate(UPDATED_CREATED_DATE)
            .success(UPDATED_SUCCESS);

        restExtractOrganisationAuditMockMvc.perform(put("/api/extract-organisation-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedExtractOrganisationAudit)))
            .andExpect(status().isOk());

        // Validate the ExtractOrganisationAudit in the database
        List<ExtractOrganisationAudit> extractOrganisationAuditList = extractOrganisationAuditRepository.findAll();
        assertThat(extractOrganisationAuditList).hasSize(databaseSizeBeforeUpdate);
        ExtractOrganisationAudit testExtractOrganisationAudit = extractOrganisationAuditList.get(extractOrganisationAuditList.size() - 1);
        assertThat(testExtractOrganisationAudit.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testExtractOrganisationAudit.isSuccess()).isEqualTo(UPDATED_SUCCESS);
    }

    @Test
    @Transactional
    public void updateNonExistingExtractOrganisationAudit() throws Exception {
        int databaseSizeBeforeUpdate = extractOrganisationAuditRepository.findAll().size();

        // Create the ExtractOrganisationAudit

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExtractOrganisationAuditMockMvc.perform(put("/api/extract-organisation-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extractOrganisationAudit)))
            .andExpect(status().isBadRequest());

        // Validate the ExtractOrganisationAudit in the database
        List<ExtractOrganisationAudit> extractOrganisationAuditList = extractOrganisationAuditRepository.findAll();
        assertThat(extractOrganisationAuditList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExtractOrganisationAudit() throws Exception {
        // Initialize the database
        extractOrganisationAuditRepository.saveAndFlush(extractOrganisationAudit);

        int databaseSizeBeforeDelete = extractOrganisationAuditRepository.findAll().size();

        // Delete the extractOrganisationAudit
        restExtractOrganisationAuditMockMvc.perform(delete("/api/extract-organisation-audits/{id}", extractOrganisationAudit.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ExtractOrganisationAudit> extractOrganisationAuditList = extractOrganisationAuditRepository.findAll();
        assertThat(extractOrganisationAuditList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExtractOrganisationAudit.class);
        ExtractOrganisationAudit extractOrganisationAudit1 = new ExtractOrganisationAudit();
        extractOrganisationAudit1.setId(1L);
        ExtractOrganisationAudit extractOrganisationAudit2 = new ExtractOrganisationAudit();
        extractOrganisationAudit2.setId(extractOrganisationAudit1.getId());
        assertThat(extractOrganisationAudit1).isEqualTo(extractOrganisationAudit2);
        extractOrganisationAudit2.setId(2L);
        assertThat(extractOrganisationAudit1).isNotEqualTo(extractOrganisationAudit2);
        extractOrganisationAudit1.setId(null);
        assertThat(extractOrganisationAudit1).isNotEqualTo(extractOrganisationAudit2);
    }
}
