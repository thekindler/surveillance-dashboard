package com.infosys.surveillance.repository;

import com.infosys.surveillance.domain.SurveillanceTask;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SurveillanceTask entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SurveillanceTaskRepository extends JpaRepository<SurveillanceTask, Long> {
}
