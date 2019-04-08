package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.ExtractManagerCharlie2App;

import io.github.jhipster.application.domain.ExtractConfig;
import io.github.jhipster.application.repository.ExtractConfigRepository;
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
 * Test class for the ExtractConfigResource REST controller.
 *
 * @see ExtractConfigResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExtractManagerCharlie2App.class)
public class ExtractConfigResourceIntTest {

    private static final String DEFAULT_EXTRACTNAME = "AAAAAAAAAA";
    private static final String UPDATED_EXTRACTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_REQUESTINGORG = "AAAAAAAAAA";
    private static final String UPDATED_REQUESTINGORG = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final Boolean DEFAULT_DELETED = false;
    private static final Boolean UPDATED_DELETED = true;

    private static final String DEFAULT_EMAIL_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_CONTACT = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ExtractConfigRepository extractConfigRepository;

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

    private MockMvc restExtractConfigMockMvc;

    private ExtractConfig extractConfig;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExtractConfigResource extractConfigResource = new ExtractConfigResource(extractConfigRepository);
        this.restExtractConfigMockMvc = MockMvcBuilders.standaloneSetup(extractConfigResource)
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
    public static ExtractConfig createEntity(EntityManager em) {
        ExtractConfig extractConfig = new ExtractConfig()
            .extractname(DEFAULT_EXTRACTNAME)
            .type(DEFAULT_TYPE)
            .requestingorg(DEFAULT_REQUESTINGORG)
            .active(DEFAULT_ACTIVE)
            .deleted(DEFAULT_DELETED)
            .emailContact(DEFAULT_EMAIL_CONTACT)
            .createdDate(DEFAULT_CREATED_DATE);
        return extractConfig;
    }

    @Before
    public void initTest() {
        extractConfig = createEntity(em);
    }

    @Test
    @Transactional
    public void createExtractConfig() throws Exception {
        int databaseSizeBeforeCreate = extractConfigRepository.findAll().size();

        // Create the ExtractConfig
        restExtractConfigMockMvc.perform(post("/api/extract-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extractConfig)))
            .andExpect(status().isCreated());

        // Validate the ExtractConfig in the database
        List<ExtractConfig> extractConfigList = extractConfigRepository.findAll();
        assertThat(extractConfigList).hasSize(databaseSizeBeforeCreate + 1);
        ExtractConfig testExtractConfig = extractConfigList.get(extractConfigList.size() - 1);
        assertThat(testExtractConfig.getExtractname()).isEqualTo(DEFAULT_EXTRACTNAME);
        assertThat(testExtractConfig.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testExtractConfig.getRequestingorg()).isEqualTo(DEFAULT_REQUESTINGORG);
        assertThat(testExtractConfig.isActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testExtractConfig.isDeleted()).isEqualTo(DEFAULT_DELETED);
        assertThat(testExtractConfig.getEmailContact()).isEqualTo(DEFAULT_EMAIL_CONTACT);
        assertThat(testExtractConfig.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
    }

    @Test
    @Transactional
    public void createExtractConfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = extractConfigRepository.findAll().size();

        // Create the ExtractConfig with an existing ID
        extractConfig.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExtractConfigMockMvc.perform(post("/api/extract-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extractConfig)))
            .andExpect(status().isBadRequest());

        // Validate the ExtractConfig in the database
        List<ExtractConfig> extractConfigList = extractConfigRepository.findAll();
        assertThat(extractConfigList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllExtractConfigs() throws Exception {
        // Initialize the database
        extractConfigRepository.saveAndFlush(extractConfig);

        // Get all the extractConfigList
        restExtractConfigMockMvc.perform(get("/api/extract-configs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(extractConfig.getId().intValue())))
            .andExpect(jsonPath("$.[*].extractname").value(hasItem(DEFAULT_EXTRACTNAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].requestingorg").value(hasItem(DEFAULT_REQUESTINGORG.toString())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].emailContact").value(hasItem(DEFAULT_EMAIL_CONTACT.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getExtractConfig() throws Exception {
        // Initialize the database
        extractConfigRepository.saveAndFlush(extractConfig);

        // Get the extractConfig
        restExtractConfigMockMvc.perform(get("/api/extract-configs/{id}", extractConfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(extractConfig.getId().intValue()))
            .andExpect(jsonPath("$.extractname").value(DEFAULT_EXTRACTNAME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.requestingorg").value(DEFAULT_REQUESTINGORG.toString()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.booleanValue()))
            .andExpect(jsonPath("$.emailContact").value(DEFAULT_EMAIL_CONTACT.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingExtractConfig() throws Exception {
        // Get the extractConfig
        restExtractConfigMockMvc.perform(get("/api/extract-configs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExtractConfig() throws Exception {
        // Initialize the database
        extractConfigRepository.saveAndFlush(extractConfig);

        int databaseSizeBeforeUpdate = extractConfigRepository.findAll().size();

        // Update the extractConfig
        ExtractConfig updatedExtractConfig = extractConfigRepository.findById(extractConfig.getId()).get();
        // Disconnect from session so that the updates on updatedExtractConfig are not directly saved in db
        em.detach(updatedExtractConfig);
        updatedExtractConfig
            .extractname(UPDATED_EXTRACTNAME)
            .type(UPDATED_TYPE)
            .requestingorg(UPDATED_REQUESTINGORG)
            .active(UPDATED_ACTIVE)
            .deleted(UPDATED_DELETED)
            .emailContact(UPDATED_EMAIL_CONTACT)
            .createdDate(UPDATED_CREATED_DATE);

        restExtractConfigMockMvc.perform(put("/api/extract-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedExtractConfig)))
            .andExpect(status().isOk());

        // Validate the ExtractConfig in the database
        List<ExtractConfig> extractConfigList = extractConfigRepository.findAll();
        assertThat(extractConfigList).hasSize(databaseSizeBeforeUpdate);
        ExtractConfig testExtractConfig = extractConfigList.get(extractConfigList.size() - 1);
        assertThat(testExtractConfig.getExtractname()).isEqualTo(UPDATED_EXTRACTNAME);
        assertThat(testExtractConfig.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testExtractConfig.getRequestingorg()).isEqualTo(UPDATED_REQUESTINGORG);
        assertThat(testExtractConfig.isActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testExtractConfig.isDeleted()).isEqualTo(UPDATED_DELETED);
        assertThat(testExtractConfig.getEmailContact()).isEqualTo(UPDATED_EMAIL_CONTACT);
        assertThat(testExtractConfig.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingExtractConfig() throws Exception {
        int databaseSizeBeforeUpdate = extractConfigRepository.findAll().size();

        // Create the ExtractConfig

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExtractConfigMockMvc.perform(put("/api/extract-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extractConfig)))
            .andExpect(status().isBadRequest());

        // Validate the ExtractConfig in the database
        List<ExtractConfig> extractConfigList = extractConfigRepository.findAll();
        assertThat(extractConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExtractConfig() throws Exception {
        // Initialize the database
        extractConfigRepository.saveAndFlush(extractConfig);

        int databaseSizeBeforeDelete = extractConfigRepository.findAll().size();

        // Delete the extractConfig
        restExtractConfigMockMvc.perform(delete("/api/extract-configs/{id}", extractConfig.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ExtractConfig> extractConfigList = extractConfigRepository.findAll();
        assertThat(extractConfigList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExtractConfig.class);
        ExtractConfig extractConfig1 = new ExtractConfig();
        extractConfig1.setId(1L);
        ExtractConfig extractConfig2 = new ExtractConfig();
        extractConfig2.setId(extractConfig1.getId());
        assertThat(extractConfig1).isEqualTo(extractConfig2);
        extractConfig2.setId(2L);
        assertThat(extractConfig1).isNotEqualTo(extractConfig2);
        extractConfig1.setId(null);
        assertThat(extractConfig1).isNotEqualTo(extractConfig2);
    }
}
