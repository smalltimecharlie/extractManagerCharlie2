package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.ExtractManagerCharlie2App;

import io.github.jhipster.application.domain.ExtractParts;
import io.github.jhipster.application.repository.ExtractPartsRepository;
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
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ExtractPartsResource REST controller.
 *
 * @see ExtractPartsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExtractManagerCharlie2App.class)
public class ExtractPartsResourceIntTest {

    private static final String DEFAULT_EXTRACT_PART = "AAAAAAAAAA";
    private static final String UPDATED_EXTRACT_PART = "BBBBBBBBBB";

    private static final Integer DEFAULT_RETENTION_PERIOD = 1;
    private static final Integer UPDATED_RETENTION_PERIOD = 2;

    private static final String DEFAULT_FILE_FORMAT = "AAAAAAAAAA";
    private static final String UPDATED_FILE_FORMAT = "BBBBBBBBBB";

    private static final Integer DEFAULT_RETRY_COUNT = 1;
    private static final Integer UPDATED_RETRY_COUNT = 2;

    private static final String DEFAULT_SCHEDULE = "AAAAAAAAAA";
    private static final String UPDATED_SCHEDULE = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_FILE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_COMPRESSION = "AAAAAAAAAA";
    private static final String UPDATED_COMPRESSION = "BBBBBBBBBB";

    private static final String DEFAULT_P_GP_CERT = "AAAAAAAAAA";
    private static final String UPDATED_P_GP_CERT = "BBBBBBBBBB";

    private static final String DEFAULT_S_FTP_PUB_KEY = "AAAAAAAAAA";
    private static final String UPDATED_S_FTP_PUB_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_S_FTP_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_S_FTP_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_ENCRYPTION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ENCRYPTION_TYPE = "BBBBBBBBBB";

    @Autowired
    private ExtractPartsRepository extractPartsRepository;

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

    private MockMvc restExtractPartsMockMvc;

    private ExtractParts extractParts;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExtractPartsResource extractPartsResource = new ExtractPartsResource(extractPartsRepository);
        this.restExtractPartsMockMvc = MockMvcBuilders.standaloneSetup(extractPartsResource)
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
    public static ExtractParts createEntity(EntityManager em) {
        ExtractParts extractParts = new ExtractParts()
            .extractPart(DEFAULT_EXTRACT_PART)
            .retentionPeriod(DEFAULT_RETENTION_PERIOD)
            .fileFormat(DEFAULT_FILE_FORMAT)
            .retryCount(DEFAULT_RETRY_COUNT)
            .schedule(DEFAULT_SCHEDULE)
            .fileType(DEFAULT_FILE_TYPE)
            .compression(DEFAULT_COMPRESSION)
            .pGPCert(DEFAULT_P_GP_CERT)
            .sFTPPubKey(DEFAULT_S_FTP_PUB_KEY)
            .sFTPUsername(DEFAULT_S_FTP_USERNAME)
            .encryptionType(DEFAULT_ENCRYPTION_TYPE);
        return extractParts;
    }

    @Before
    public void initTest() {
        extractParts = createEntity(em);
    }

    @Test
    @Transactional
    public void createExtractParts() throws Exception {
        int databaseSizeBeforeCreate = extractPartsRepository.findAll().size();

        // Create the ExtractParts
        restExtractPartsMockMvc.perform(post("/api/extract-parts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extractParts)))
            .andExpect(status().isCreated());

        // Validate the ExtractParts in the database
        List<ExtractParts> extractPartsList = extractPartsRepository.findAll();
        assertThat(extractPartsList).hasSize(databaseSizeBeforeCreate + 1);
        ExtractParts testExtractParts = extractPartsList.get(extractPartsList.size() - 1);
        assertThat(testExtractParts.getExtractPart()).isEqualTo(DEFAULT_EXTRACT_PART);
        assertThat(testExtractParts.getRetentionPeriod()).isEqualTo(DEFAULT_RETENTION_PERIOD);
        assertThat(testExtractParts.getFileFormat()).isEqualTo(DEFAULT_FILE_FORMAT);
        assertThat(testExtractParts.getRetryCount()).isEqualTo(DEFAULT_RETRY_COUNT);
        assertThat(testExtractParts.getSchedule()).isEqualTo(DEFAULT_SCHEDULE);
        assertThat(testExtractParts.getFileType()).isEqualTo(DEFAULT_FILE_TYPE);
        assertThat(testExtractParts.getCompression()).isEqualTo(DEFAULT_COMPRESSION);
        assertThat(testExtractParts.getpGPCert()).isEqualTo(DEFAULT_P_GP_CERT);
        assertThat(testExtractParts.getsFTPPubKey()).isEqualTo(DEFAULT_S_FTP_PUB_KEY);
        assertThat(testExtractParts.getsFTPUsername()).isEqualTo(DEFAULT_S_FTP_USERNAME);
        assertThat(testExtractParts.getEncryptionType()).isEqualTo(DEFAULT_ENCRYPTION_TYPE);
    }

    @Test
    @Transactional
    public void createExtractPartsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = extractPartsRepository.findAll().size();

        // Create the ExtractParts with an existing ID
        extractParts.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExtractPartsMockMvc.perform(post("/api/extract-parts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extractParts)))
            .andExpect(status().isBadRequest());

        // Validate the ExtractParts in the database
        List<ExtractParts> extractPartsList = extractPartsRepository.findAll();
        assertThat(extractPartsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllExtractParts() throws Exception {
        // Initialize the database
        extractPartsRepository.saveAndFlush(extractParts);

        // Get all the extractPartsList
        restExtractPartsMockMvc.perform(get("/api/extract-parts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(extractParts.getId().intValue())))
            .andExpect(jsonPath("$.[*].extractPart").value(hasItem(DEFAULT_EXTRACT_PART.toString())))
            .andExpect(jsonPath("$.[*].retentionPeriod").value(hasItem(DEFAULT_RETENTION_PERIOD)))
            .andExpect(jsonPath("$.[*].fileFormat").value(hasItem(DEFAULT_FILE_FORMAT.toString())))
            .andExpect(jsonPath("$.[*].retryCount").value(hasItem(DEFAULT_RETRY_COUNT)))
            .andExpect(jsonPath("$.[*].schedule").value(hasItem(DEFAULT_SCHEDULE.toString())))
            .andExpect(jsonPath("$.[*].fileType").value(hasItem(DEFAULT_FILE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].compression").value(hasItem(DEFAULT_COMPRESSION.toString())))
            .andExpect(jsonPath("$.[*].pGPCert").value(hasItem(DEFAULT_P_GP_CERT.toString())))
            .andExpect(jsonPath("$.[*].sFTPPubKey").value(hasItem(DEFAULT_S_FTP_PUB_KEY.toString())))
            .andExpect(jsonPath("$.[*].sFTPUsername").value(hasItem(DEFAULT_S_FTP_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].encryptionType").value(hasItem(DEFAULT_ENCRYPTION_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getExtractParts() throws Exception {
        // Initialize the database
        extractPartsRepository.saveAndFlush(extractParts);

        // Get the extractParts
        restExtractPartsMockMvc.perform(get("/api/extract-parts/{id}", extractParts.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(extractParts.getId().intValue()))
            .andExpect(jsonPath("$.extractPart").value(DEFAULT_EXTRACT_PART.toString()))
            .andExpect(jsonPath("$.retentionPeriod").value(DEFAULT_RETENTION_PERIOD))
            .andExpect(jsonPath("$.fileFormat").value(DEFAULT_FILE_FORMAT.toString()))
            .andExpect(jsonPath("$.retryCount").value(DEFAULT_RETRY_COUNT))
            .andExpect(jsonPath("$.schedule").value(DEFAULT_SCHEDULE.toString()))
            .andExpect(jsonPath("$.fileType").value(DEFAULT_FILE_TYPE.toString()))
            .andExpect(jsonPath("$.compression").value(DEFAULT_COMPRESSION.toString()))
            .andExpect(jsonPath("$.pGPCert").value(DEFAULT_P_GP_CERT.toString()))
            .andExpect(jsonPath("$.sFTPPubKey").value(DEFAULT_S_FTP_PUB_KEY.toString()))
            .andExpect(jsonPath("$.sFTPUsername").value(DEFAULT_S_FTP_USERNAME.toString()))
            .andExpect(jsonPath("$.encryptionType").value(DEFAULT_ENCRYPTION_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingExtractParts() throws Exception {
        // Get the extractParts
        restExtractPartsMockMvc.perform(get("/api/extract-parts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExtractParts() throws Exception {
        // Initialize the database
        extractPartsRepository.saveAndFlush(extractParts);

        int databaseSizeBeforeUpdate = extractPartsRepository.findAll().size();

        // Update the extractParts
        ExtractParts updatedExtractParts = extractPartsRepository.findById(extractParts.getId()).get();
        // Disconnect from session so that the updates on updatedExtractParts are not directly saved in db
        em.detach(updatedExtractParts);
        updatedExtractParts
            .extractPart(UPDATED_EXTRACT_PART)
            .retentionPeriod(UPDATED_RETENTION_PERIOD)
            .fileFormat(UPDATED_FILE_FORMAT)
            .retryCount(UPDATED_RETRY_COUNT)
            .schedule(UPDATED_SCHEDULE)
            .fileType(UPDATED_FILE_TYPE)
            .compression(UPDATED_COMPRESSION)
            .pGPCert(UPDATED_P_GP_CERT)
            .sFTPPubKey(UPDATED_S_FTP_PUB_KEY)
            .sFTPUsername(UPDATED_S_FTP_USERNAME)
            .encryptionType(UPDATED_ENCRYPTION_TYPE);

        restExtractPartsMockMvc.perform(put("/api/extract-parts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedExtractParts)))
            .andExpect(status().isOk());

        // Validate the ExtractParts in the database
        List<ExtractParts> extractPartsList = extractPartsRepository.findAll();
        assertThat(extractPartsList).hasSize(databaseSizeBeforeUpdate);
        ExtractParts testExtractParts = extractPartsList.get(extractPartsList.size() - 1);
        assertThat(testExtractParts.getExtractPart()).isEqualTo(UPDATED_EXTRACT_PART);
        assertThat(testExtractParts.getRetentionPeriod()).isEqualTo(UPDATED_RETENTION_PERIOD);
        assertThat(testExtractParts.getFileFormat()).isEqualTo(UPDATED_FILE_FORMAT);
        assertThat(testExtractParts.getRetryCount()).isEqualTo(UPDATED_RETRY_COUNT);
        assertThat(testExtractParts.getSchedule()).isEqualTo(UPDATED_SCHEDULE);
        assertThat(testExtractParts.getFileType()).isEqualTo(UPDATED_FILE_TYPE);
        assertThat(testExtractParts.getCompression()).isEqualTo(UPDATED_COMPRESSION);
        assertThat(testExtractParts.getpGPCert()).isEqualTo(UPDATED_P_GP_CERT);
        assertThat(testExtractParts.getsFTPPubKey()).isEqualTo(UPDATED_S_FTP_PUB_KEY);
        assertThat(testExtractParts.getsFTPUsername()).isEqualTo(UPDATED_S_FTP_USERNAME);
        assertThat(testExtractParts.getEncryptionType()).isEqualTo(UPDATED_ENCRYPTION_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingExtractParts() throws Exception {
        int databaseSizeBeforeUpdate = extractPartsRepository.findAll().size();

        // Create the ExtractParts

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExtractPartsMockMvc.perform(put("/api/extract-parts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extractParts)))
            .andExpect(status().isBadRequest());

        // Validate the ExtractParts in the database
        List<ExtractParts> extractPartsList = extractPartsRepository.findAll();
        assertThat(extractPartsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExtractParts() throws Exception {
        // Initialize the database
        extractPartsRepository.saveAndFlush(extractParts);

        int databaseSizeBeforeDelete = extractPartsRepository.findAll().size();

        // Delete the extractParts
        restExtractPartsMockMvc.perform(delete("/api/extract-parts/{id}", extractParts.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ExtractParts> extractPartsList = extractPartsRepository.findAll();
        assertThat(extractPartsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExtractParts.class);
        ExtractParts extractParts1 = new ExtractParts();
        extractParts1.setId(1L);
        ExtractParts extractParts2 = new ExtractParts();
        extractParts2.setId(extractParts1.getId());
        assertThat(extractParts1).isEqualTo(extractParts2);
        extractParts2.setId(2L);
        assertThat(extractParts1).isNotEqualTo(extractParts2);
        extractParts1.setId(null);
        assertThat(extractParts1).isNotEqualTo(extractParts2);
    }
}
