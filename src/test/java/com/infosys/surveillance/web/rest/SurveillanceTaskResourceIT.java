package com.infosys.surveillance.web.rest;

import com.infosys.surveillance.SurveillanceApp;
import com.infosys.surveillance.config.TestSecurityConfiguration;
import com.infosys.surveillance.domain.SurveillanceTask;
import com.infosys.surveillance.repository.SurveillanceTaskRepository;
import com.infosys.surveillance.service.SurveillanceTaskService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SurveillanceTaskResource} REST controller.
 */
@SpringBootTest(classes = { SurveillanceApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class SurveillanceTaskResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MODEL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VERSION_ID = "AAAAAAAAAA";
    private static final String UPDATED_VERSION_ID = "BBBBBBBBBB";

    @Autowired
    private SurveillanceTaskRepository surveillanceTaskRepository;

    @Autowired
    private SurveillanceTaskService surveillanceTaskService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSurveillanceTaskMockMvc;

    private SurveillanceTask surveillanceTask;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SurveillanceTask createEntity(EntityManager em) {
        SurveillanceTask surveillanceTask = new SurveillanceTask()
            .type(DEFAULT_TYPE)
            .modelName(DEFAULT_MODEL_NAME)
            .versionId(DEFAULT_VERSION_ID);
        return surveillanceTask;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SurveillanceTask createUpdatedEntity(EntityManager em) {
        SurveillanceTask surveillanceTask = new SurveillanceTask()
            .type(UPDATED_TYPE)
            .modelName(UPDATED_MODEL_NAME)
            .versionId(UPDATED_VERSION_ID);
        return surveillanceTask;
    }

    @BeforeEach
    public void initTest() {
        surveillanceTask = createEntity(em);
    }

    @Test
    @Transactional
    public void createSurveillanceTask() throws Exception {
        int databaseSizeBeforeCreate = surveillanceTaskRepository.findAll().size();
        // Create the SurveillanceTask
        restSurveillanceTaskMockMvc.perform(post("/api/surveillance-tasks").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(surveillanceTask)))
            .andExpect(status().isCreated());

        // Validate the SurveillanceTask in the database
        List<SurveillanceTask> surveillanceTaskList = surveillanceTaskRepository.findAll();
        assertThat(surveillanceTaskList).hasSize(databaseSizeBeforeCreate + 1);
        SurveillanceTask testSurveillanceTask = surveillanceTaskList.get(surveillanceTaskList.size() - 1);
        assertThat(testSurveillanceTask.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testSurveillanceTask.getModelName()).isEqualTo(DEFAULT_MODEL_NAME);
        assertThat(testSurveillanceTask.getVersionId()).isEqualTo(DEFAULT_VERSION_ID);
    }

    @Test
    @Transactional
    public void createSurveillanceTaskWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = surveillanceTaskRepository.findAll().size();

        // Create the SurveillanceTask with an existing ID
        surveillanceTask.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSurveillanceTaskMockMvc.perform(post("/api/surveillance-tasks").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(surveillanceTask)))
            .andExpect(status().isBadRequest());

        // Validate the SurveillanceTask in the database
        List<SurveillanceTask> surveillanceTaskList = surveillanceTaskRepository.findAll();
        assertThat(surveillanceTaskList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSurveillanceTasks() throws Exception {
        // Initialize the database
        surveillanceTaskRepository.saveAndFlush(surveillanceTask);

        // Get all the surveillanceTaskList
        restSurveillanceTaskMockMvc.perform(get("/api/surveillance-tasks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(surveillanceTask.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].modelName").value(hasItem(DEFAULT_MODEL_NAME)))
            .andExpect(jsonPath("$.[*].versionId").value(hasItem(DEFAULT_VERSION_ID)));
    }
    
    @Test
    @Transactional
    public void getSurveillanceTask() throws Exception {
        // Initialize the database
        surveillanceTaskRepository.saveAndFlush(surveillanceTask);

        // Get the surveillanceTask
        restSurveillanceTaskMockMvc.perform(get("/api/surveillance-tasks/{id}", surveillanceTask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(surveillanceTask.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.modelName").value(DEFAULT_MODEL_NAME))
            .andExpect(jsonPath("$.versionId").value(DEFAULT_VERSION_ID));
    }
    @Test
    @Transactional
    public void getNonExistingSurveillanceTask() throws Exception {
        // Get the surveillanceTask
        restSurveillanceTaskMockMvc.perform(get("/api/surveillance-tasks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSurveillanceTask() throws Exception {
        // Initialize the database
        surveillanceTaskService.save(surveillanceTask);

        int databaseSizeBeforeUpdate = surveillanceTaskRepository.findAll().size();

        // Update the surveillanceTask
        SurveillanceTask updatedSurveillanceTask = surveillanceTaskRepository.findById(surveillanceTask.getId()).get();
        // Disconnect from session so that the updates on updatedSurveillanceTask are not directly saved in db
        em.detach(updatedSurveillanceTask);
        updatedSurveillanceTask
            .type(UPDATED_TYPE)
            .modelName(UPDATED_MODEL_NAME)
            .versionId(UPDATED_VERSION_ID);

        restSurveillanceTaskMockMvc.perform(put("/api/surveillance-tasks").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSurveillanceTask)))
            .andExpect(status().isOk());

        // Validate the SurveillanceTask in the database
        List<SurveillanceTask> surveillanceTaskList = surveillanceTaskRepository.findAll();
        assertThat(surveillanceTaskList).hasSize(databaseSizeBeforeUpdate);
        SurveillanceTask testSurveillanceTask = surveillanceTaskList.get(surveillanceTaskList.size() - 1);
        assertThat(testSurveillanceTask.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testSurveillanceTask.getModelName()).isEqualTo(UPDATED_MODEL_NAME);
        assertThat(testSurveillanceTask.getVersionId()).isEqualTo(UPDATED_VERSION_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingSurveillanceTask() throws Exception {
        int databaseSizeBeforeUpdate = surveillanceTaskRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSurveillanceTaskMockMvc.perform(put("/api/surveillance-tasks").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(surveillanceTask)))
            .andExpect(status().isBadRequest());

        // Validate the SurveillanceTask in the database
        List<SurveillanceTask> surveillanceTaskList = surveillanceTaskRepository.findAll();
        assertThat(surveillanceTaskList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSurveillanceTask() throws Exception {
        // Initialize the database
        surveillanceTaskService.save(surveillanceTask);

        int databaseSizeBeforeDelete = surveillanceTaskRepository.findAll().size();

        // Delete the surveillanceTask
        restSurveillanceTaskMockMvc.perform(delete("/api/surveillance-tasks/{id}", surveillanceTask.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SurveillanceTask> surveillanceTaskList = surveillanceTaskRepository.findAll();
        assertThat(surveillanceTaskList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
