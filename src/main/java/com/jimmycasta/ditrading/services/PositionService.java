package com.jimmycasta.ditrading.services;

import com.jimmycasta.ditrading.entities.PositionEntity;

import java.util.List;

public interface PositionService {

    List<PositionEntity> getAll();

    PositionEntity getById(int id);

    PositionEntity save(PositionEntity position);

    void delete(int id);

}
