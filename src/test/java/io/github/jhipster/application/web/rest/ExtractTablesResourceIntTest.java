package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.ExtractManagerCharlie2App;

import io.github.jhipster.application.domain.ExtractTables;
import io.github.jhipster.application.repository.ExtractTablesRepository;
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
 * Test class for the ExtractTablesResource REST controller.
 *
 * @see ExtractTablesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExtractManagerCharlie2App.class)
public class ExtractTablesResourceIntTest {

    private static final String DEFAULT_TABLENAME = "AAAAAAAAAA";
    private static final String UPDATED_TABLENAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_EXTRACTABLE = false;
    private static final Boolean UPDATED_IS_EXTRACTABLE = true;

    @Autowired
    private ExtractTablesRepository extractTablesRepository;

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

    private MockMvc restExtractTablesMockMvc;

    private ExtractTables extractTables;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExtractTablesResource extractTablesResource = new ExtractTablesResource(extractTablesRepository);
        this.restExtractTablesMockMvc = MockMvcBuilders.standaloneSetup(extractTablesResource)
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
    public static ExtractTables createEntity(EntityManager em) {
        ExtractTables extractTables = new ExtractTables()
            .tablename(DEFAULT_TABLENAME)
            .isExtractable(DEFAULT_IS_EXTRACTABLE);
        return extractTables;
    }

    @Before
    public void initTest() {
        extractTables = createEntity(em);
    }

    @Test
    @Transactional
    public void createExtractTables() throws Exception {
        int databaseSizeBeforeCreate = extractTablesRepository.findAll().size();

        // Create the ExtractTables
        restExtractTablesMockMvc.perform(post("/api/extract-tables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extractTables)))
            .andExpect(status().isCreated());

        // Validate the ExtractTables in the database
        List<ExtractTables> extractTablesList = extractTablesRepository.findAll();
        assertThat(extractTablesList).hasSize(databaseSizeBeforeCreate + 1);
        ExtractTables testExtractTables = extractTablesList.get(extractTablesList.size() - 1);
        assertThat(testExtractTables.getTablename()).isEqualTo(DEFAULT_TABLENAME);
        assertThat(testExtractTables.isIsExtractable()).isEqualTo(DEFAULT_IS_EXTRACTABLE);
    }

    @Test
    @Transactional
    public void createExtractTablesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = extractTablesRepository.findAll().size();

        // Create the ExtractTables with an existing ID
        extractTables.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExtractTablesMockMvc.perform(post("/api/extract-tables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extractTables)))
            .andExpect(status().isBadRequest());

        // Validate the ExtractTables in the database
        List<ExtractTables> extractTablesList = extractTablesRepository.findAll();
        assertThat(extractTablesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllExtractTables() throws Exception {
        // Initialize the database
        extractTablesRepository.saveAndFlush(extractTables);

        // Get all the extractTablesList
        restExtractTablesMockMvc.perform(get("/api/extract-tables?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(extractTables.getId().intValue())))
            .andExpect(jsonPath("$.[*].tablename").value(hasItem(DEFAULT_TABLENAME.toString())))
            .andExpect(jsonPath("$.[*].isExtractable").value(hasItem(DEFAULT_IS_EXTRACTABLE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getExtractTables() throws Exception {
        // Initialize the database
        extractTablesRepository.saveAndFlush(extractTables);

        // Get the extractTables
        restExtractTablesMockMvc.perform(get("/api/extract-tables/{id}", extractTables.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(extractTables.getId().intValue()))
            .andExpect(jsonPath("$.tablename").value(DEFAULT_TABLENAME.toString()))
            .andExpect(jsonPath("$.isExtractable").value(DEFAULT_IS_EXTRACTABLE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingExtractTables() throws Exception {
        // Get the extractTables
        restExtractTablesMockMvc.perform(get("/api/extract-tables/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExtractTables() throws Exception {
        // Initialize the database
        extractTablesRepository.saveAndFlush(extractTables);

        int databaseSizeBeforeUpdate = extractTablesRepository.findAll().size();

        // Update the extractTables
        ExtractTables updatedExtractTables = extractTablesRepository.findById(extractTables.getId()).get();
        // Disconnect from session so that the updates on updatedExtractTables are not directly saved in db
        em.detach(updatedExtractTables);
        updatedExtractTables
            .tablename(UPDATED_TABLENAME)
            .isExtractable(UPDATED_IS_EXTRACTABLE);

        restExtractTablesMockMvc.perform(put("/api/extract-tables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedExtractTables)))
            .andExpect(status().isOk());

        // Validate the ExtractTables in the database
        List<ExtractTables> extractTablesList = extractTablesRepository.findAll();
        assertThat(extractTablesList).hasSize(databaseSizeBeforeUpdate);
        ExtractTables testExtractTables = extractTablesList.get(extractTablesList.size() - 1);
        assertThat(testExtractTables.getTablename()).isEqualTo(UPDATED_TABLENAME);
        assertThat(testExtractTables.isIsExtractable()).isEqualTo(UPDATED_IS_EXTRACTABLE);
    }

    @Test
    @Transactional
    public void updateNonExistingExtractTables() throws Exception {
        int databaseSizeBeforeUpdate = extractTablesRepository.findAll().size();

        // Create the ExtractTables

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExtractTablesMockMvc.perform(put("/api/extract-tables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extractTables)))
            .andExpect(status().isBadRequest());

        // Validate the ExtractTables in the database
        List<ExtractTables> extractTablesList = extractTablesRepository.findAll();
        assertThat(extractTablesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExtractTables() throws Exception {
        // Initialize the database
        extractTablesRepository.saveAndFlush(extractTables);

        int databaseSizeBeforeDelete = extractTablesRepository.findAll().size();

        // Delete the extractTables
        restExtractTablesMockMvc.perform(delete("/api/extract-tables/{id}", extractTables.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ExtractTables> extractTablesList = extractTablesRepository.findAll();
        assertThat(extractTablesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExtractTables.class);
        ExtractTables extractTables1 = new ExtractTables();
        extractTables1.setId(1L);
        ExtractTables extractTables2 = new ExtractTables();
        extractTables2.setId(extractTables1.getId());
        assertThat(extractTables1).isEqualTo(extractTables2);
        extractTables2.setId(2L);
        assertThat(extractTables1).isNotEqualTo(extractTables2);
        extractTables1.setId(null);
        assertThat(extractTables1).isNotEqualTo(extractTables2);
    }
}
