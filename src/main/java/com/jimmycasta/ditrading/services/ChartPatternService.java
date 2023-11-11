package com.jimmycasta.ditrading.services;

import com.jimmycasta.ditrading.entities.ChartPatternEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChartPatternService {

    Page<ChartPatternEntity> getAll(Pageable pageable);

    ChartPatternEntity getById(int id);

    ChartPatternEntity save(ChartPatternEntity pattern);

    void delete(int id);
}
