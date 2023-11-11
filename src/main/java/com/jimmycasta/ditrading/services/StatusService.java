package com.jimmycasta.ditrading.services;

import com.jimmycasta.ditrading.entities.StatusEntity;

import java.util.List;

public interface StatusService {

    List<StatusEntity> getAll();

    StatusEntity getById(int id);

    StatusEntity save(StatusEntity status);

    void delete(int id);
}
