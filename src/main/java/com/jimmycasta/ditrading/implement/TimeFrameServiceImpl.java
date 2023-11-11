package com.jimmycasta.ditrading.implement;

import com.jimmycasta.ditrading.entities.TimeFrameEntity;
import com.jimmycasta.ditrading.repositories.TimeFrameRepository;
import com.jimmycasta.ditrading.services.TimeFrameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeFrameServiceImpl implements TimeFrameService {

    private final TimeFrameRepository timeFrameRepository;

    @Autowired
    public TimeFrameServiceImpl(TimeFrameRepository timeFrameRepository) {
        this.timeFrameRepository = timeFrameRepository;
    }


    @Override
    public Page<TimeFrameEntity> getAllPage(Pageable pageable) {
        return timeFrameRepository.findAll(pageable);
    }

    @Override
    public List<TimeFrameEntity> getAll() {
        return timeFrameRepository.findAll();
    }

    @Override
    public TimeFrameEntity getById(int id) {
        Optional<TimeFrameEntity> optional = timeFrameRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public TimeFrameEntity save(TimeFrameEntity timeFrame) {
        return timeFrameRepository.save(timeFrame);
    }

    @Override
    public TimeFrameEntity edit(int id) {
        Optional<TimeFrameEntity> optional = timeFrameRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        timeFrameRepository.deleteById(id);
    }
}
