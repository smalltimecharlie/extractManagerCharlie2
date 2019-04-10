package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.ExtractManagerCharlie2App;

import io.github.jhipster.application.domain.ExtractDetails;
import io.github.jhipster.application.repository.ExtractDetailsRepository;
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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ExtractDetailsResource REST controller.
 *
 * @see ExtractDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExtractManagerCharlie2App.class)
public class ExtractDetailsResourceIntTest {

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    private static final byte[] DEFAULT_VALUE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_VALUE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_VALUE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_VALUE_CONTENT_TYPE = "image/png";

    @Autowired
    private ExtractDetailsRepository extractDetailsRepository;

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

    private MockMvc restExtractDetailsMockMvc;

    private ExtractDetails extractDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExtractDetailsResource extractDetailsResource = new ExtractDetailsResource(extractDetailsRepository);
        this.restExtractDetailsMockMvc = MockMvcBuilders.standaloneSetup(extractDetailsResource)
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
    public static ExtractDetails createEntity(EntityManager em) {
        ExtractDetails extractDetails = new ExtractDetails()
            .key(DEFAULT_KEY)
            .value(DEFAULT_VALUE)
            .valueContentType(DEFAULT_VALUE_CONTENT_TYPE);
        return extractDetails;
    }

    @Before
    public void initTest() {
        extractDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createExtractDetails() throws Exception {
        int databaseSizeBeforeCreate = extractDetailsRepository.findAll().size();

        // Create the ExtractDetails
        restExtractDetailsMockMvc.perform(post("/api/extract-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extractDetails)))
            .andExpect(status().isCreated());

        // Validate the ExtractDetails in the database
        List<ExtractDetails> extractDetailsList = extractDetailsRepository.findAll();
        assertThat(extractDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        ExtractDetails testExtractDetails = extractDetailsList.get(extractDetailsList.size() - 1);
        assertThat(testExtractDetails.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testExtractDetails.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testExtractDetails.getValueContentType()).isEqualTo(DEFAULT_VALUE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createExtractDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = extractDetailsRepository.findAll().size();

        // Create the ExtractDetails with an existing ID
        extractDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExtractDetailsMockMvc.perform(post("/api/extract-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extractDetails)))
            .andExpect(status().isBadRequest());

        // Validate the ExtractDetails in the database
        List<ExtractDetails> extractDetailsList = extractDetailsRepository.findAll();
        assertThat(extractDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllExtractDetails() throws Exception {
        // Initialize the database
        extractDetailsRepository.saveAndFlush(extractDetails);

        // Get all the extractDetailsList
        restExtractDetailsMockMvc.perform(get("/api/extract-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(extractDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].valueContentType").value(hasItem(DEFAULT_VALUE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(Base64Utils.encodeToString(DEFAULT_VALUE))));
    }
    
    @Test
    @Transactional
    public void getExtractDetails() throws Exception {
        // Initialize the database
        extractDetailsRepository.saveAndFlush(extractDetails);

        // Get the extractDetails
        restExtractDetailsMockMvc.perform(get("/api/extract-details/{id}", extractDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(extractDetails.getId().intValue()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY.toString()))
            .andExpect(jsonPath("$.valueContentType").value(DEFAULT_VALUE_CONTENT_TYPE))
            .andExpect(jsonPath("$.value").value(Base64Utils.encodeToString(DEFAULT_VALUE)));
    }

    @Test
    @Transactional
    public void getNonExistingExtractDetails() throws Exception {
        // Get the extractDetails
        restExtractDetailsMockMvc.perform(get("/api/extract-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExtractDetails() throws Exception {
        // Initialize the database
        extractDetailsRepository.saveAndFlush(extractDetails);

        int databaseSizeBeforeUpdate = extractDetailsRepository.findAll().size();

        // Update the extractDetails
        ExtractDetails updatedExtractDetails = extractDetailsRepository.findById(extractDetails.getId()).get();
        // Disconnect from session so that the updates on updatedExtractDetails are not directly saved in db
        em.detach(updatedExtractDetails);
        updatedExtractDetails
            .key(UPDATED_KEY)
            .value(UPDATED_VALUE)
            .valueContentType(UPDATED_VALUE_CONTENT_TYPE);

        restExtractDetailsMockMvc.perform(put("/api/extract-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedExtractDetails)))
            .andExpect(status().isOk());

        // Validate the ExtractDetails in the database
        List<ExtractDetails> extractDetailsList = extractDetailsRepository.findAll();
        assertThat(extractDetailsList).hasSize(databaseSizeBeforeUpdate);
        ExtractDetails testExtractDetails = extractDetailsList.get(extractDetailsList.size() - 1);
        assertThat(testExtractDetails.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testExtractDetails.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testExtractDetails.getValueContentType()).isEqualTo(UPDATED_VALUE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingExtractDetails() throws Exception {
        int databaseSizeBeforeUpdate = extractDetailsRepository.findAll().size();

        // Create the ExtractDetails

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExtractDetailsMockMvc.perform(put("/api/extract-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extractDetails)))
            .andExpect(status().isBadRequest());

        // Validate the ExtractDetails in the database
        List<ExtractDetails> extractDetailsList = extractDetailsRepository.findAll();
        assertThat(extractDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExtractDetails() throws Exception {
        // Initialize the database
        extractDetailsRepository.saveAndFlush(extractDetails);

        int databaseSizeBeforeDelete = extractDetailsRepository.findAll().size();

        // Delete the extractDetails
        restExtractDetailsMockMvc.perform(delete("/api/extract-details/{id}", extractDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ExtractDetails> extractDetailsList = extractDetailsRepository.findAll();
        assertThat(extractDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExtractDetails.class);
        ExtractDetails extractDetails1 = new ExtractDetails();
        extractDetails1.setId(1L);
        ExtractDetails extractDetails2 = new ExtractDetails();
        extractDetails2.setId(extractDetails1.getId());
        assertThat(extractDetails1).isEqualTo(extractDetails2);
        extractDetails2.setId(2L);
        assertThat(extractDetails1).isNotEqualTo(extractDetails2);
        extractDetails1.setId(null);
        assertThat(extractDetails1).isNotEqualTo(extractDetails2);
    }
}
