package com.infosys.surveillance.web.rest;

import com.infosys.surveillance.domain.SurveillanceTask;
import com.infosys.surveillance.service.SurveillanceTaskService;
import com.infosys.surveillance.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.infosys.surveillance.domain.SurveillanceTask}.
 */
@RestController
@RequestMapping("/api")
public class SurveillanceTaskResource {

    private final Logger log = LoggerFactory.getLogger(SurveillanceTaskResource.class);

    private static final String ENTITY_NAME = "surveillanceTask";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SurveillanceTaskService surveillanceTaskService;

    public SurveillanceTaskResource(SurveillanceTaskService surveillanceTaskService) {
        this.surveillanceTaskService = surveillanceTaskService;
    }

    /**
     * {@code POST  /surveillance-tasks} : Create a new surveillanceTask.
     *
     * @param surveillanceTask the surveillanceTask to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new surveillanceTask, or with status {@code 400 (Bad Request)} if the surveillanceTask has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/surveillance-tasks")
    public ResponseEntity<SurveillanceTask> createSurveillanceTask(@RequestBody SurveillanceTask surveillanceTask) throws URISyntaxException {
        log.debug("REST request to save SurveillanceTask : {}", surveillanceTask);
        if (surveillanceTask.getId() != null) {
            throw new BadRequestAlertException("A new surveillanceTask cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SurveillanceTask result = surveillanceTaskService.save(surveillanceTask);
        return ResponseEntity.created(new URI("/api/surveillance-tasks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /surveillance-tasks} : Updates an existing surveillanceTask.
     *
     * @param surveillanceTask the surveillanceTask to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated surveillanceTask,
     * or with status {@code 400 (Bad Request)} if the surveillanceTask is not valid,
     * or with status {@code 500 (Internal Server Error)} if the surveillanceTask couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/surveillance-tasks")
    public ResponseEntity<SurveillanceTask> updateSurveillanceTask(@RequestBody SurveillanceTask surveillanceTask) throws URISyntaxException {
        log.debug("REST request to update SurveillanceTask : {}", surveillanceTask);
        if (surveillanceTask.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SurveillanceTask result = surveillanceTaskService.save(surveillanceTask);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, surveillanceTask.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /surveillance-tasks} : get all the surveillanceTasks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of surveillanceTasks in body.
     */
    @GetMapping("/surveillance-tasks")
    public List<SurveillanceTask> getAllSurveillanceTasks() {
        log.debug("REST request to get all SurveillanceTasks");
        return surveillanceTaskService.findAll();
    }

    /**
     * {@code GET  /surveillance-tasks/:id} : get the "id" surveillanceTask.
     *
     * @param id the id of the surveillanceTask to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the surveillanceTask, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/surveillance-tasks/{id}")
    public ResponseEntity<SurveillanceTask> getSurveillanceTask(@PathVariable Long id) {
        log.debug("REST request to get SurveillanceTask : {}", id);
        Optional<SurveillanceTask> surveillanceTask = surveillanceTaskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(surveillanceTask);
    }

    /**
     * {@code DELETE  /surveillance-tasks/:id} : delete the "id" surveillanceTask.
     *
     * @param id the id of the surveillanceTask to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/surveillance-tasks/{id}")
    public ResponseEntity<Void> deleteSurveillanceTask(@PathVariable Long id) {
        log.debug("REST request to delete SurveillanceTask : {}", id);
        surveillanceTaskService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
