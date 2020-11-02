package com.infosys.surveillance.service;

import com.infosys.surveillance.domain.SurveillanceTask;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link SurveillanceTask}.
 */
public interface SurveillanceTaskService {

    /**
     * Save a surveillanceTask.
     *
     * @param surveillanceTask the entity to save.
     * @return the persisted entity.
     */
    SurveillanceTask save(SurveillanceTask surveillanceTask);

    /**
     * Get all the surveillanceTasks.
     *
     * @return the list of entities.
     */
    List<SurveillanceTask> findAll();


    /**
     * Get the "id" surveillanceTask.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SurveillanceTask> findOne(Long id);

    /**
     * Delete the "id" surveillanceTask.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
