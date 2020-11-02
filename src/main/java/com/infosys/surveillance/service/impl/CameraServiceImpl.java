package com.infosys.surveillance.service.impl;

import com.infosys.surveillance.service.CameraService;
import com.infosys.surveillance.domain.Camera;
import com.infosys.surveillance.repository.CameraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Camera}.
 */
@Service
@Transactional
public class CameraServiceImpl implements CameraService {

    private final Logger log = LoggerFactory.getLogger(CameraServiceImpl.class);

    private final CameraRepository cameraRepository;

    public CameraServiceImpl(CameraRepository cameraRepository) {
        this.cameraRepository = cameraRepository;
    }

    @Override
    public Camera save(Camera camera) {
        log.debug("Request to save Camera : {}", camera);
        return cameraRepository.save(camera);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Camera> findAll() {
        log.debug("Request to get all Cameras");
        return cameraRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Camera> findOne(Long id) {
        log.debug("Request to get Camera : {}", id);
        return cameraRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Camera : {}", id);
        cameraRepository.deleteById(id);
    }
}
