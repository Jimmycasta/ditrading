package com.jimmycasta.ditrading.implement;

import com.jimmycasta.ditrading.entities.ChartPatternEntity;
import com.jimmycasta.ditrading.repositories.ChartPatternRepository;
import com.jimmycasta.ditrading.services.ChartPatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChartPatternImpl implements ChartPatternService {

    private final ChartPatternRepository chartPatternRepository;

    @Autowired
    public ChartPatternImpl(ChartPatternRepository chartPatternRepository) {
        this.chartPatternRepository = chartPatternRepository;
    }

    @Override
    public Page<ChartPatternEntity> getAllPage(Pageable pageable) {
        return chartPatternRepository.findAll(pageable);
    }

    @Override
    public List<ChartPatternEntity> getAll() {
        return chartPatternRepository.findAll();
    }

    @Override
    public ChartPatternEntity getById(int id) {
        Optional<ChartPatternEntity> optional = chartPatternRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public ChartPatternEntity save(ChartPatternEntity pattern) {
        return chartPatternRepository.save(pattern);
    }

    @Override
    public void delete(int id) {
        chartPatternRepository.deleteById(id);

    }
}
