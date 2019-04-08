package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.ExtractManagerCharlie2App;

import io.github.jhipster.application.domain.ExtractOrganisation;
import io.github.jhipster.application.repository.ExtractOrganisationRepository;
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
 * Test class for the ExtractOrganisationResource REST controller.
 *
 * @see ExtractOrganisationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExtractManagerCharlie2App.class)
public class ExtractOrganisationResourceIntTest {

    private static final String DEFAULT_ORGANISATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORGANISATION_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ExtractOrganisationRepository extractOrganisationRepository;

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

    private MockMvc restExtractOrganisationMockMvc;

    private ExtractOrganisation extractOrganisation;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExtractOrganisationResource extractOrganisationResource = new ExtractOrganisationResource(extractOrganisationRepository);
        this.restExtractOrganisationMockMvc = MockMvcBuilders.standaloneSetup(extractOrganisationResource)
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
    public static ExtractOrganisation createEntity(EntityManager em) {
        ExtractOrganisation extractOrganisation = new ExtractOrganisation()
            .organisationName(DEFAULT_ORGANISATION_NAME)
            .modifiedDate(DEFAULT_MODIFIED_DATE)
            .createdDate(DEFAULT_CREATED_DATE);
        return extractOrganisation;
    }

    @Before
    public void initTest() {
        extractOrganisation = createEntity(em);
    }

    @Test
    @Transactional
    public void createExtractOrganisation() throws Exception {
        int databaseSizeBeforeCreate = extractOrganisationRepository.findAll().size();

        // Create the ExtractOrganisation
        restExtractOrganisationMockMvc.perform(post("/api/extract-organisations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extractOrganisation)))
            .andExpect(status().isCreated());

        // Validate the ExtractOrganisation in the database
        List<ExtractOrganisation> extractOrganisationList = extractOrganisationRepository.findAll();
        assertThat(extractOrganisationList).hasSize(databaseSizeBeforeCreate + 1);
        ExtractOrganisation testExtractOrganisation = extractOrganisationList.get(extractOrganisationList.size() - 1);
        assertThat(testExtractOrganisation.getOrganisationName()).isEqualTo(DEFAULT_ORGANISATION_NAME);
        assertThat(testExtractOrganisation.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
        assertThat(testExtractOrganisation.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
    }

    @Test
    @Transactional
    public void createExtractOrganisationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = extractOrganisationRepository.findAll().size();

        // Create the ExtractOrganisation with an existing ID
        extractOrganisation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExtractOrganisationMockMvc.perform(post("/api/extract-organisations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extractOrganisation)))
            .andExpect(status().isBadRequest());

        // Validate the ExtractOrganisation in the database
        List<ExtractOrganisation> extractOrganisationList = extractOrganisationRepository.findAll();
        assertThat(extractOrganisationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllExtractOrganisations() throws Exception {
        // Initialize the database
        extractOrganisationRepository.saveAndFlush(extractOrganisation);

        // Get all the extractOrganisationList
        restExtractOrganisationMockMvc.perform(get("/api/extract-organisations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(extractOrganisation.getId().intValue())))
            .andExpect(jsonPath("$.[*].organisationName").value(hasItem(DEFAULT_ORGANISATION_NAME.toString())))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getExtractOrganisation() throws Exception {
        // Initialize the database
        extractOrganisationRepository.saveAndFlush(extractOrganisation);

        // Get the extractOrganisation
        restExtractOrganisationMockMvc.perform(get("/api/extract-organisations/{id}", extractOrganisation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(extractOrganisation.getId().intValue()))
            .andExpect(jsonPath("$.organisationName").value(DEFAULT_ORGANISATION_NAME.toString()))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingExtractOrganisation() throws Exception {
        // Get the extractOrganisation
        restExtractOrganisationMockMvc.perform(get("/api/extract-organisations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExtractOrganisation() throws Exception {
        // Initialize the database
        extractOrganisationRepository.saveAndFlush(extractOrganisation);

        int databaseSizeBeforeUpdate = extractOrganisationRepository.findAll().size();

        // Update the extractOrganisation
        ExtractOrganisation updatedExtractOrganisation = extractOrganisationRepository.findById(extractOrganisation.getId()).get();
        // Disconnect from session so that the updates on updatedExtractOrganisation are not directly saved in db
        em.detach(updatedExtractOrganisation);
        updatedExtractOrganisation
            .organisationName(UPDATED_ORGANISATION_NAME)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdDate(UPDATED_CREATED_DATE);

        restExtractOrganisationMockMvc.perform(put("/api/extract-organisations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedExtractOrganisation)))
            .andExpect(status().isOk());

        // Validate the ExtractOrganisation in the database
        List<ExtractOrganisation> extractOrganisationList = extractOrganisationRepository.findAll();
        assertThat(extractOrganisationList).hasSize(databaseSizeBeforeUpdate);
        ExtractOrganisation testExtractOrganisation = extractOrganisationList.get(extractOrganisationList.size() - 1);
        assertThat(testExtractOrganisation.getOrganisationName()).isEqualTo(UPDATED_ORGANISATION_NAME);
        assertThat(testExtractOrganisation.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testExtractOrganisation.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingExtractOrganisation() throws Exception {
        int databaseSizeBeforeUpdate = extractOrganisationRepository.findAll().size();

        // Create the ExtractOrganisation

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExtractOrganisationMockMvc.perform(put("/api/extract-organisations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extractOrganisation)))
            .andExpect(status().isBadRequest());

        // Validate the ExtractOrganisation in the database
        List<ExtractOrganisation> extractOrganisationList = extractOrganisationRepository.findAll();
        assertThat(extractOrganisationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExtractOrganisation() throws Exception {
        // Initialize the database
        extractOrganisationRepository.saveAndFlush(extractOrganisation);

        int databaseSizeBeforeDelete = extractOrganisationRepository.findAll().size();

        // Delete the extractOrganisation
        restExtractOrganisationMockMvc.perform(delete("/api/extract-organisations/{id}", extractOrganisation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ExtractOrganisation> extractOrganisationList = extractOrganisationRepository.findAll();
        assertThat(extractOrganisationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExtractOrganisation.class);
        ExtractOrganisation extractOrganisation1 = new ExtractOrganisation();
        extractOrganisation1.setId(1L);
        ExtractOrganisation extractOrganisation2 = new ExtractOrganisation();
        extractOrganisation2.setId(extractOrganisation1.getId());
        assertThat(extractOrganisation1).isEqualTo(extractOrganisation2);
        extractOrganisation2.setId(2L);
        assertThat(extractOrganisation1).isNotEqualTo(extractOrganisation2);
        extractOrganisation1.setId(null);
        assertThat(extractOrganisation1).isNotEqualTo(extractOrganisation2);
    }
}
