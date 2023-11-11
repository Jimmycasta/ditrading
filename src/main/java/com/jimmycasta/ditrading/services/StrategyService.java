package com.jimmycasta.ditrading.services;


import com.jimmycasta.ditrading.entities.StrategyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StrategyService {

    Page<StrategyEntity> getAllPage(Pageable pageable);

    List<StrategyEntity>getAll();

    StrategyEntity getById(int id);

    StrategyEntity save(StrategyEntity strategy);

    void delete(int id);
}
