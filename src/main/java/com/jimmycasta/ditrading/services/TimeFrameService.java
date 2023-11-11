package com.jimmycasta.ditrading.services;

import com.jimmycasta.ditrading.entities.TimeFrameEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TimeFrameService {

    Page<TimeFrameEntity> getAllPage(Pageable pageable);

    List<TimeFrameEntity> getAll();
    TimeFrameEntity getById(int id);

    TimeFrameEntity save(TimeFrameEntity timeFrame);

    TimeFrameEntity edit(int id);

    void delete(int id);
}
