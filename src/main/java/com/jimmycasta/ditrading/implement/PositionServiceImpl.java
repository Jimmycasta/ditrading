package com.jimmycasta.ditrading.implement;

import com.jimmycasta.ditrading.entities.PositionEntity;
import com.jimmycasta.ditrading.repositories.PositionRepository;
import com.jimmycasta.ditrading.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public List<PositionEntity> getAll() {
        return positionRepository.findAll();
    }

    @Override
    public PositionEntity getById(int id) {
        Optional<PositionEntity> optional = positionRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public PositionEntity save(PositionEntity position) {
        return positionRepository.save(position);
    }

    @Override
    public void delete(int id) {
        positionRepository.deleteById(id);

    }
}
