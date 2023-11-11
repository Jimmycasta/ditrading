package com.jimmycasta.ditrading.implement;

import com.jimmycasta.ditrading.entities.TradeEntity;
import com.jimmycasta.ditrading.repositories.TradeRepository;
import com.jimmycasta.ditrading.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeServiceImpl implements TradeService {

    private final TradeRepository tradeRepository;

    @Autowired
    public TradeServiceImpl(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public List<TradeEntity> getAll() {
        return tradeRepository.findAll();
    }

    @Override
    public Page<TradeEntity> getAllPage(Pageable pageable) {
        return tradeRepository.findAll(pageable);
    }

    @Override
    public TradeEntity getById(int id) {
        Optional<TradeEntity> optional = tradeRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public TradeEntity save(TradeEntity trade) {
        return tradeRepository.save(trade);

    }

    @Override
    public void delete(int id) {
        tradeRepository.deleteById(id);

    }
}
