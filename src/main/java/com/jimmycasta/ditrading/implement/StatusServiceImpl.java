package com.jimmycasta.ditrading.implement;

import com.jimmycasta.ditrading.entities.StatusEntity;
import com.jimmycasta.ditrading.repositories.StatusRepository;
import com.jimmycasta.ditrading.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public List<StatusEntity> getAll() {
        return statusRepository.findAll();
    }

    @Override
    public StatusEntity getById(int id) {
        Optional<StatusEntity> optional = statusRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public StatusEntity save(StatusEntity status) {
        return statusRepository.save(status);
    }

    @Override
    public void delete(int id) {
        statusRepository.deleteById(id);

    }
}
