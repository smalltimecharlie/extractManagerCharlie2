package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.ExtractManagerCharlie2App;

import io.github.jhipster.application.domain.Organisation;
import io.github.jhipster.application.repository.OrganisationRepository;
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
 * Test class for the OrganisationResource REST controller.
 *
 * @see OrganisationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExtractManagerCharlie2App.class)
public class OrganisationResourceIntTest {

    private static final Integer DEFAULT_C_DB = 1;
    private static final Integer UPDATED_C_DB = 2;

    private static final String DEFAULT_ORGANISATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORGANISATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NATIONAL_PRACTICE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_NATIONAL_PRACTICE_CODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    @Autowired
    private OrganisationRepository organisationRepository;

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

    private MockMvc restOrganisationMockMvc;

    private Organisation organisation;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrganisationResource organisationResource = new OrganisationResource(organisationRepository);
        this.restOrganisationMockMvc = MockMvcBuilders.standaloneSetup(organisationResource)
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
    public static Organisation createEntity(EntityManager em) {
        Organisation organisation = new Organisation()
            .cDB(DEFAULT_C_DB)
            .organisationName(DEFAULT_ORGANISATION_NAME)
            .nationalPracticeCode(DEFAULT_NATIONAL_PRACTICE_CODE)
            .active(DEFAULT_ACTIVE);
        return organisation;
    }

    @Before
    public void initTest() {
        organisation = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrganisation() throws Exception {
        int databaseSizeBeforeCreate = organisationRepository.findAll().size();

        // Create the Organisation
        restOrganisationMockMvc.perform(post("/api/organisations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(organisation)))
            .andExpect(status().isCreated());

        // Validate the Organisation in the database
        List<Organisation> organisationList = organisationRepository.findAll();
        assertThat(organisationList).hasSize(databaseSizeBeforeCreate + 1);
        Organisation testOrganisation = organisationList.get(organisationList.size() - 1);
        assertThat(testOrganisation.getcDB()).isEqualTo(DEFAULT_C_DB);
        assertThat(testOrganisation.getOrganisationName()).isEqualTo(DEFAULT_ORGANISATION_NAME);
        assertThat(testOrganisation.getNationalPracticeCode()).isEqualTo(DEFAULT_NATIONAL_PRACTICE_CODE);
        assertThat(testOrganisation.isActive()).isEqualTo(DEFAULT_ACTIVE);
    }

    @Test
    @Transactional
    public void createOrganisationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = organisationRepository.findAll().size();

        // Create the Organisation with an existing ID
        organisation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrganisationMockMvc.perform(post("/api/organisations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(organisation)))
            .andExpect(status().isBadRequest());

        // Validate the Organisation in the database
        List<Organisation> organisationList = organisationRepository.findAll();
        assertThat(organisationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOrganisations() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get all the organisationList
        restOrganisationMockMvc.perform(get("/api/organisations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(organisation.getId().intValue())))
            .andExpect(jsonPath("$.[*].cDB").value(hasItem(DEFAULT_C_DB)))
            .andExpect(jsonPath("$.[*].organisationName").value(hasItem(DEFAULT_ORGANISATION_NAME.toString())))
            .andExpect(jsonPath("$.[*].nationalPracticeCode").value(hasItem(DEFAULT_NATIONAL_PRACTICE_CODE.toString())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getOrganisation() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        // Get the organisation
        restOrganisationMockMvc.perform(get("/api/organisations/{id}", organisation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(organisation.getId().intValue()))
            .andExpect(jsonPath("$.cDB").value(DEFAULT_C_DB))
            .andExpect(jsonPath("$.organisationName").value(DEFAULT_ORGANISATION_NAME.toString()))
            .andExpect(jsonPath("$.nationalPracticeCode").value(DEFAULT_NATIONAL_PRACTICE_CODE.toString()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingOrganisation() throws Exception {
        // Get the organisation
        restOrganisationMockMvc.perform(get("/api/organisations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrganisation() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        int databaseSizeBeforeUpdate = organisationRepository.findAll().size();

        // Update the organisation
        Organisation updatedOrganisation = organisationRepository.findById(organisation.getId()).get();
        // Disconnect from session so that the updates on updatedOrganisation are not directly saved in db
        em.detach(updatedOrganisation);
        updatedOrganisation
            .cDB(UPDATED_C_DB)
            .organisationName(UPDATED_ORGANISATION_NAME)
            .nationalPracticeCode(UPDATED_NATIONAL_PRACTICE_CODE)
            .active(UPDATED_ACTIVE);

        restOrganisationMockMvc.perform(put("/api/organisations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrganisation)))
            .andExpect(status().isOk());

        // Validate the Organisation in the database
        List<Organisation> organisationList = organisationRepository.findAll();
        assertThat(organisationList).hasSize(databaseSizeBeforeUpdate);
        Organisation testOrganisation = organisationList.get(organisationList.size() - 1);
        assertThat(testOrganisation.getcDB()).isEqualTo(UPDATED_C_DB);
        assertThat(testOrganisation.getOrganisationName()).isEqualTo(UPDATED_ORGANISATION_NAME);
        assertThat(testOrganisation.getNationalPracticeCode()).isEqualTo(UPDATED_NATIONAL_PRACTICE_CODE);
        assertThat(testOrganisation.isActive()).isEqualTo(UPDATED_ACTIVE);
    }

    @Test
    @Transactional
    public void updateNonExistingOrganisation() throws Exception {
        int databaseSizeBeforeUpdate = organisationRepository.findAll().size();

        // Create the Organisation

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrganisationMockMvc.perform(put("/api/organisations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(organisation)))
            .andExpect(status().isBadRequest());

        // Validate the Organisation in the database
        List<Organisation> organisationList = organisationRepository.findAll();
        assertThat(organisationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrganisation() throws Exception {
        // Initialize the database
        organisationRepository.saveAndFlush(organisation);

        int databaseSizeBeforeDelete = organisationRepository.findAll().size();

        // Delete the organisation
        restOrganisationMockMvc.perform(delete("/api/organisations/{id}", organisation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Organisation> organisationList = organisationRepository.findAll();
        assertThat(organisationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Organisation.class);
        Organisation organisation1 = new Organisation();
        organisation1.setId(1L);
        Organisation organisation2 = new Organisation();
        organisation2.setId(organisation1.getId());
        assertThat(organisation1).isEqualTo(organisation2);
        organisation2.setId(2L);
        assertThat(organisation1).isNotEqualTo(organisation2);
        organisation1.setId(null);
        assertThat(organisation1).isNotEqualTo(organisation2);
    }
}
