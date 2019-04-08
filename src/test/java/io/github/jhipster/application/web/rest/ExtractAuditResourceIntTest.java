package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.ExtractManagerCharlie2App;

import io.github.jhipster.application.domain.ExtractAudit;
import io.github.jhipster.application.repository.ExtractAuditRepository;
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
 * Test class for the ExtractAuditResource REST controller.
 *
 * @see ExtractAuditResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExtractManagerCharlie2App.class)
public class ExtractAuditResourceIntTest {

    private static final String DEFAULT_EXTRACT_PART = "AAAAAAAAAA";
    private static final String UPDATED_EXTRACT_PART = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRACT_START_TIME = "AAAAAAAAAA";
    private static final String UPDATED_EXTRACT_START_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRACT_END_TIME = "AAAAAAAAAA";
    private static final String UPDATED_EXTRACT_END_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_EXTRACT_POINT = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_EXTRACT_POINT = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_EXTRACT_POINT = "AAAAAAAAAA";
    private static final String UPDATED_LAST_EXTRACT_POINT = "BBBBBBBBBB";

    private static final String DEFAULT_OUTPUT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OUTPUT_FILE_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_RECORD_COUNT = 1;
    private static final Integer UPDATED_RECORD_COUNT = 2;

    private static final Integer DEFAULT_FILE_SIZE = 1;
    private static final Integer UPDATED_FILE_SIZE = 2;

    private static final String DEFAULT_AIRFLOW_URL = "AAAAAAAAAA";
    private static final String UPDATED_AIRFLOW_URL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SUCCESS = false;
    private static final Boolean UPDATED_SUCCESS = true;

    @Autowired
    private ExtractAuditRepository extractAuditRepository;

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

    private MockMvc restExtractAuditMockMvc;

    private ExtractAudit extractAudit;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExtractAuditResource extractAuditResource = new ExtractAuditResource(extractAuditRepository);
        this.restExtractAuditMockMvc = MockMvcBuilders.standaloneSetup(extractAuditResource)
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
    public static ExtractAudit createEntity(EntityManager em) {
        ExtractAudit extractAudit = new ExtractAudit()
            .extractPart(DEFAULT_EXTRACT_PART)
            .createdDate(DEFAULT_CREATED_DATE)
            .message(DEFAULT_MESSAGE)
            .extractStartTime(DEFAULT_EXTRACT_START_TIME)
            .extractEndTime(DEFAULT_EXTRACT_END_TIME)
            .firstExtractPoint(DEFAULT_FIRST_EXTRACT_POINT)
            .lastExtractPoint(DEFAULT_LAST_EXTRACT_POINT)
            .outputFileName(DEFAULT_OUTPUT_FILE_NAME)
            .recordCount(DEFAULT_RECORD_COUNT)
            .fileSize(DEFAULT_FILE_SIZE)
            .airflowUrl(DEFAULT_AIRFLOW_URL)
            .success(DEFAULT_SUCCESS);
        return extractAudit;
    }

    @Before
    public void initTest() {
        extractAudit = createEntity(em);
    }

    @Test
    @Transactional
    public void createExtractAudit() throws Exception {
        int databaseSizeBeforeCreate = extractAuditRepository.findAll().size();

        // Create the ExtractAudit
        restExtractAuditMockMvc.perform(post("/api/extract-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extractAudit)))
            .andExpect(status().isCreated());

        // Validate the ExtractAudit in the database
        List<ExtractAudit> extractAuditList = extractAuditRepository.findAll();
        assertThat(extractAuditList).hasSize(databaseSizeBeforeCreate + 1);
        ExtractAudit testExtractAudit = extractAuditList.get(extractAuditList.size() - 1);
        assertThat(testExtractAudit.getExtractPart()).isEqualTo(DEFAULT_EXTRACT_PART);
        assertThat(testExtractAudit.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testExtractAudit.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testExtractAudit.getExtractStartTime()).isEqualTo(DEFAULT_EXTRACT_START_TIME);
        assertThat(testExtractAudit.getExtractEndTime()).isEqualTo(DEFAULT_EXTRACT_END_TIME);
        assertThat(testExtractAudit.getFirstExtractPoint()).isEqualTo(DEFAULT_FIRST_EXTRACT_POINT);
        assertThat(testExtractAudit.getLastExtractPoint()).isEqualTo(DEFAULT_LAST_EXTRACT_POINT);
        assertThat(testExtractAudit.getOutputFileName()).isEqualTo(DEFAULT_OUTPUT_FILE_NAME);
        assertThat(testExtractAudit.getRecordCount()).isEqualTo(DEFAULT_RECORD_COUNT);
        assertThat(testExtractAudit.getFileSize()).isEqualTo(DEFAULT_FILE_SIZE);
        assertThat(testExtractAudit.getAirflowUrl()).isEqualTo(DEFAULT_AIRFLOW_URL);
        assertThat(testExtractAudit.isSuccess()).isEqualTo(DEFAULT_SUCCESS);
    }

    @Test
    @Transactional
    public void createExtractAuditWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = extractAuditRepository.findAll().size();

        // Create the ExtractAudit with an existing ID
        extractAudit.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExtractAuditMockMvc.perform(post("/api/extract-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extractAudit)))
            .andExpect(status().isBadRequest());

        // Validate the ExtractAudit in the database
        List<ExtractAudit> extractAuditList = extractAuditRepository.findAll();
        assertThat(extractAuditList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllExtractAudits() throws Exception {
        // Initialize the database
        extractAuditRepository.saveAndFlush(extractAudit);

        // Get all the extractAuditList
        restExtractAuditMockMvc.perform(get("/api/extract-audits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(extractAudit.getId().intValue())))
            .andExpect(jsonPath("$.[*].extractPart").value(hasItem(DEFAULT_EXTRACT_PART.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].extractStartTime").value(hasItem(DEFAULT_EXTRACT_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].extractEndTime").value(hasItem(DEFAULT_EXTRACT_END_TIME.toString())))
            .andExpect(jsonPath("$.[*].firstExtractPoint").value(hasItem(DEFAULT_FIRST_EXTRACT_POINT.toString())))
            .andExpect(jsonPath("$.[*].lastExtractPoint").value(hasItem(DEFAULT_LAST_EXTRACT_POINT.toString())))
            .andExpect(jsonPath("$.[*].outputFileName").value(hasItem(DEFAULT_OUTPUT_FILE_NAME.toString())))
            .andExpect(jsonPath("$.[*].recordCount").value(hasItem(DEFAULT_RECORD_COUNT)))
            .andExpect(jsonPath("$.[*].fileSize").value(hasItem(DEFAULT_FILE_SIZE)))
            .andExpect(jsonPath("$.[*].airflowUrl").value(hasItem(DEFAULT_AIRFLOW_URL.toString())))
            .andExpect(jsonPath("$.[*].success").value(hasItem(DEFAULT_SUCCESS.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getExtractAudit() throws Exception {
        // Initialize the database
        extractAuditRepository.saveAndFlush(extractAudit);

        // Get the extractAudit
        restExtractAuditMockMvc.perform(get("/api/extract-audits/{id}", extractAudit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(extractAudit.getId().intValue()))
            .andExpect(jsonPath("$.extractPart").value(DEFAULT_EXTRACT_PART.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()))
            .andExpect(jsonPath("$.extractStartTime").value(DEFAULT_EXTRACT_START_TIME.toString()))
            .andExpect(jsonPath("$.extractEndTime").value(DEFAULT_EXTRACT_END_TIME.toString()))
            .andExpect(jsonPath("$.firstExtractPoint").value(DEFAULT_FIRST_EXTRACT_POINT.toString()))
            .andExpect(jsonPath("$.lastExtractPoint").value(DEFAULT_LAST_EXTRACT_POINT.toString()))
            .andExpect(jsonPath("$.outputFileName").value(DEFAULT_OUTPUT_FILE_NAME.toString()))
            .andExpect(jsonPath("$.recordCount").value(DEFAULT_RECORD_COUNT))
            .andExpect(jsonPath("$.fileSize").value(DEFAULT_FILE_SIZE))
            .andExpect(jsonPath("$.airflowUrl").value(DEFAULT_AIRFLOW_URL.toString()))
            .andExpect(jsonPath("$.success").value(DEFAULT_SUCCESS.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingExtractAudit() throws Exception {
        // Get the extractAudit
        restExtractAuditMockMvc.perform(get("/api/extract-audits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExtractAudit() throws Exception {
        // Initialize the database
        extractAuditRepository.saveAndFlush(extractAudit);

        int databaseSizeBeforeUpdate = extractAuditRepository.findAll().size();

        // Update the extractAudit
        ExtractAudit updatedExtractAudit = extractAuditRepository.findById(extractAudit.getId()).get();
        // Disconnect from session so that the updates on updatedExtractAudit are not directly saved in db
        em.detach(updatedExtractAudit);
        updatedExtractAudit
            .extractPart(UPDATED_EXTRACT_PART)
            .createdDate(UPDATED_CREATED_DATE)
            .message(UPDATED_MESSAGE)
            .extractStartTime(UPDATED_EXTRACT_START_TIME)
            .extractEndTime(UPDATED_EXTRACT_END_TIME)
            .firstExtractPoint(UPDATED_FIRST_EXTRACT_POINT)
            .lastExtractPoint(UPDATED_LAST_EXTRACT_POINT)
            .outputFileName(UPDATED_OUTPUT_FILE_NAME)
            .recordCount(UPDATED_RECORD_COUNT)
            .fileSize(UPDATED_FILE_SIZE)
            .airflowUrl(UPDATED_AIRFLOW_URL)
            .success(UPDATED_SUCCESS);

        restExtractAuditMockMvc.perform(put("/api/extract-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedExtractAudit)))
            .andExpect(status().isOk());

        // Validate the ExtractAudit in the database
        List<ExtractAudit> extractAuditList = extractAuditRepository.findAll();
        assertThat(extractAuditList).hasSize(databaseSizeBeforeUpdate);
        ExtractAudit testExtractAudit = extractAuditList.get(extractAuditList.size() - 1);
        assertThat(testExtractAudit.getExtractPart()).isEqualTo(UPDATED_EXTRACT_PART);
        assertThat(testExtractAudit.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testExtractAudit.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testExtractAudit.getExtractStartTime()).isEqualTo(UPDATED_EXTRACT_START_TIME);
        assertThat(testExtractAudit.getExtractEndTime()).isEqualTo(UPDATED_EXTRACT_END_TIME);
        assertThat(testExtractAudit.getFirstExtractPoint()).isEqualTo(UPDATED_FIRST_EXTRACT_POINT);
        assertThat(testExtractAudit.getLastExtractPoint()).isEqualTo(UPDATED_LAST_EXTRACT_POINT);
        assertThat(testExtractAudit.getOutputFileName()).isEqualTo(UPDATED_OUTPUT_FILE_NAME);
        assertThat(testExtractAudit.getRecordCount()).isEqualTo(UPDATED_RECORD_COUNT);
        assertThat(testExtractAudit.getFileSize()).isEqualTo(UPDATED_FILE_SIZE);
        assertThat(testExtractAudit.getAirflowUrl()).isEqualTo(UPDATED_AIRFLOW_URL);
        assertThat(testExtractAudit.isSuccess()).isEqualTo(UPDATED_SUCCESS);
    }

    @Test
    @Transactional
    public void updateNonExistingExtractAudit() throws Exception {
        int databaseSizeBeforeUpdate = extractAuditRepository.findAll().size();

        // Create the ExtractAudit

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExtractAuditMockMvc.perform(put("/api/extract-audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extractAudit)))
            .andExpect(status().isBadRequest());

        // Validate the ExtractAudit in the database
        List<ExtractAudit> extractAuditList = extractAuditRepository.findAll();
        assertThat(extractAuditList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExtractAudit() throws Exception {
        // Initialize the database
        extractAuditRepository.saveAndFlush(extractAudit);

        int databaseSizeBeforeDelete = extractAuditRepository.findAll().size();

        // Delete the extractAudit
        restExtractAuditMockMvc.perform(delete("/api/extract-audits/{id}", extractAudit.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ExtractAudit> extractAuditList = extractAuditRepository.findAll();
        assertThat(extractAuditList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExtractAudit.class);
        ExtractAudit extractAudit1 = new ExtractAudit();
        extractAudit1.setId(1L);
        ExtractAudit extractAudit2 = new ExtractAudit();
        extractAudit2.setId(extractAudit1.getId());
        assertThat(extractAudit1).isEqualTo(extractAudit2);
        extractAudit2.setId(2L);
        assertThat(extractAudit1).isNotEqualTo(extractAudit2);
        extractAudit1.setId(null);
        assertThat(extractAudit1).isNotEqualTo(extractAudit2);
    }
}
