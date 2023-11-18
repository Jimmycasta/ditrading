package com.jimmycasta.ditrading.implement;

import com.jimmycasta.ditrading.entities.StrategyEntity;
import com.jimmycasta.ditrading.repositories.StrategyRepository;
import com.jimmycasta.ditrading.services.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StrategyServiceImpl implements StrategyService {

    private final StrategyRepository strategyRepository;

    @Autowired
    public StrategyServiceImpl(StrategyRepository strategyRepository) {
        this.strategyRepository = strategyRepository;
    }

    @Override
    public Page<StrategyEntity> getAllPage(Pageable pageable) {
        return strategyRepository.findAll(pageable);
    }

    @Override
    public List<StrategyEntity> getAll() {
        return strategyRepository.findAll();
    }

    @Override
    public StrategyEntity getById(int id) {
        Optional<StrategyEntity> optional = strategyRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public StrategyEntity save(StrategyEntity strategy) {
        return strategyRepository.save(strategy);
    }

    @Override
    public void delete(int id) {
        strategyRepository.deleteById(id);

    }
}
