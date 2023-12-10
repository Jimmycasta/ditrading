package com.jimmycasta.ditrading.services;

import com.jimmycasta.ditrading.entities.ChartPatternEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChartPatternService {

    Page<ChartPatternEntity> getAllPage(Pageable pageable);

    List<ChartPatternEntity> getAll();

    ChartPatternEntity getById(int id);

    ChartPatternEntity save(ChartPatternEntity pattern);

    void delete(int id);
}
