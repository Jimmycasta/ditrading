package com.jimmycasta.ditrading.services;

import com.jimmycasta.ditrading.entities.PnLResultEntity;

import java.util.List;

public interface PnLResultService {

    List<PnLResultEntity> getAll();

    PnLResultEntity save(PnLResultEntity status);

    void delete(int id);
}
