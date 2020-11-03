package com.infosys.surveillance.web.rest;

import com.infosys.surveillance.SurveillanceApp;
import com.infosys.surveillance.config.TestSecurityConfiguration;
import com.infosys.surveillance.domain.Camera;
import com.infosys.surveillance.repository.CameraRepository;
import com.infosys.surveillance.service.CameraService;

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
 * Integration tests for the {@link CameraResource} REST controller.
 */
@SpringBootTest(classes = { SurveillanceApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class CameraResourceIT {

    private static final String DEFAULT_MAKE = "AAAAAAAAAA";
    private static final String UPDATED_MAKE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_SURVEILLANCE_TASK = "AAAAAAAAAA";
    private static final String UPDATED_SURVEILLANCE_TASK = "BBBBBBBBBB";

    private static final String DEFAULT_IP = "AAAAAAAAAA";
    private static final String UPDATED_IP = "BBBBBBBBBB";

    private static final String DEFAULT_FPS = "AAAAAAAAAA";
    private static final String UPDATED_FPS = "BBBBBBBBBB";

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private CameraService cameraService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCameraMockMvc;

    private Camera camera;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Camera createEntity(EntityManager em) {
        Camera camera = new Camera()
            .make(DEFAULT_MAKE)
            .location(DEFAULT_LOCATION)
            .surveillanceTask(DEFAULT_SURVEILLANCE_TASK)
            .ip(DEFAULT_IP)
            .fps(DEFAULT_FPS);
        return camera;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Camera createUpdatedEntity(EntityManager em) {
        Camera camera = new Camera()
            .make(UPDATED_MAKE)
            .location(UPDATED_LOCATION)
            .surveillanceTask(UPDATED_SURVEILLANCE_TASK)
            .ip(UPDATED_IP)
            .fps(UPDATED_FPS);
        return camera;
    }

    @BeforeEach
    public void initTest() {
        camera = createEntity(em);
    }

    @Test
    @Transactional
    public void createCamera() throws Exception {
        int databaseSizeBeforeCreate = cameraRepository.findAll().size();
        // Create the Camera
        restCameraMockMvc.perform(post("/api/cameras").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(camera)))
            .andExpect(status().isCreated());

        // Validate the Camera in the database
        List<Camera> cameraList = cameraRepository.findAll();
        assertThat(cameraList).hasSize(databaseSizeBeforeCreate + 1);
        Camera testCamera = cameraList.get(cameraList.size() - 1);
        assertThat(testCamera.getMake()).isEqualTo(DEFAULT_MAKE);
        assertThat(testCamera.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testCamera.getSurveillanceTask()).isEqualTo(DEFAULT_SURVEILLANCE_TASK);
        assertThat(testCamera.getIp()).isEqualTo(DEFAULT_IP);
        assertThat(testCamera.getFps()).isEqualTo(DEFAULT_FPS);
    }

    @Test
    @Transactional
    public void createCameraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cameraRepository.findAll().size();

        // Create the Camera with an existing ID
        camera.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCameraMockMvc.perform(post("/api/cameras").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(camera)))
            .andExpect(status().isBadRequest());

        // Validate the Camera in the database
        List<Camera> cameraList = cameraRepository.findAll();
        assertThat(cameraList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCameras() throws Exception {
        // Initialize the database
        cameraRepository.saveAndFlush(camera);

        // Get all the cameraList
        restCameraMockMvc.perform(get("/api/cameras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(camera.getId().intValue())))
            .andExpect(jsonPath("$.[*].make").value(hasItem(DEFAULT_MAKE)))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION)))
            .andExpect(jsonPath("$.[*].surveillanceTask").value(hasItem(DEFAULT_SURVEILLANCE_TASK)))
            .andExpect(jsonPath("$.[*].ip").value(hasItem(DEFAULT_IP)))
            .andExpect(jsonPath("$.[*].fps").value(hasItem(DEFAULT_FPS)));
    }
    
    @Test
    @Transactional
    public void getCamera() throws Exception {
        // Initialize the database
        cameraRepository.saveAndFlush(camera);

        // Get the camera
        restCameraMockMvc.perform(get("/api/cameras/{id}", camera.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(camera.getId().intValue()))
            .andExpect(jsonPath("$.make").value(DEFAULT_MAKE))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION))
            .andExpect(jsonPath("$.surveillanceTask").value(DEFAULT_SURVEILLANCE_TASK))
            .andExpect(jsonPath("$.ip").value(DEFAULT_IP))
            .andExpect(jsonPath("$.fps").value(DEFAULT_FPS));
    }
    @Test
    @Transactional
    public void getNonExistingCamera() throws Exception {
        // Get the camera
        restCameraMockMvc.perform(get("/api/cameras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCamera() throws Exception {
        // Initialize the database
        cameraService.save(camera);

        int databaseSizeBeforeUpdate = cameraRepository.findAll().size();

        // Update the camera
        Camera updatedCamera = cameraRepository.findById(camera.getId()).get();
        // Disconnect from session so that the updates on updatedCamera are not directly saved in db
        em.detach(updatedCamera);
        updatedCamera
            .make(UPDATED_MAKE)
            .location(UPDATED_LOCATION)
            .surveillanceTask(UPDATED_SURVEILLANCE_TASK)
            .ip(UPDATED_IP)
            .fps(UPDATED_FPS);

        restCameraMockMvc.perform(put("/api/cameras").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCamera)))
            .andExpect(status().isOk());

        // Validate the Camera in the database
        List<Camera> cameraList = cameraRepository.findAll();
        assertThat(cameraList).hasSize(databaseSizeBeforeUpdate);
        Camera testCamera = cameraList.get(cameraList.size() - 1);
        assertThat(testCamera.getMake()).isEqualTo(UPDATED_MAKE);
        assertThat(testCamera.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testCamera.getSurveillanceTask()).isEqualTo(UPDATED_SURVEILLANCE_TASK);
        assertThat(testCamera.getIp()).isEqualTo(UPDATED_IP);
        assertThat(testCamera.getFps()).isEqualTo(UPDATED_FPS);
    }

    @Test
    @Transactional
    public void updateNonExistingCamera() throws Exception {
        int databaseSizeBeforeUpdate = cameraRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCameraMockMvc.perform(put("/api/cameras").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(camera)))
            .andExpect(status().isBadRequest());

        // Validate the Camera in the database
        List<Camera> cameraList = cameraRepository.findAll();
        assertThat(cameraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCamera() throws Exception {
        // Initialize the database
        cameraService.save(camera);

        int databaseSizeBeforeDelete = cameraRepository.findAll().size();

        // Delete the camera
        restCameraMockMvc.perform(delete("/api/cameras/{id}", camera.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Camera> cameraList = cameraRepository.findAll();
        assertThat(cameraList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
