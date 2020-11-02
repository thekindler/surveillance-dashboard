package com.infosys.surveillance.service.impl;

import com.infosys.surveillance.service.SurveillanceTaskService;
import com.infosys.surveillance.domain.SurveillanceTask;
import com.infosys.surveillance.repository.SurveillanceTaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link SurveillanceTask}.
 */
@Service
@Transactional
public class SurveillanceTaskServiceImpl implements SurveillanceTaskService {

    private final Logger log = LoggerFactory.getLogger(SurveillanceTaskServiceImpl.class);

    private final SurveillanceTaskRepository surveillanceTaskRepository;

    public SurveillanceTaskServiceImpl(SurveillanceTaskRepository surveillanceTaskRepository) {
        this.surveillanceTaskRepository = surveillanceTaskRepository;
    }

    @Override
    public SurveillanceTask save(SurveillanceTask surveillanceTask) {
        log.debug("Request to save SurveillanceTask : {}", surveillanceTask);
        return surveillanceTaskRepository.save(surveillanceTask);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SurveillanceTask> findAll() {
        log.debug("Request to get all SurveillanceTasks");
        return surveillanceTaskRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SurveillanceTask> findOne(Long id) {
        log.debug("Request to get SurveillanceTask : {}", id);
        return surveillanceTaskRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SurveillanceTask : {}", id);
        surveillanceTaskRepository.deleteById(id);
    }
}
