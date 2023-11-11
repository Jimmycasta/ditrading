package com.jimmycasta.ditrading.services;

import com.jimmycasta.ditrading.entities.TraderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TraderService {

    Page<TraderEntity> getAll(Pageable pageable);

    TraderEntity getById(int id);

    TraderEntity save(TraderEntity trader);

    void delete(int id);
}
