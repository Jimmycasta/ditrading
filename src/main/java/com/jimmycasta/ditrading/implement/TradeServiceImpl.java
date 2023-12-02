package com.jimmycasta.ditrading.implement;

import com.jimmycasta.ditrading.entities.TradeEntity;
import com.jimmycasta.ditrading.repositories.TradeRepository;
import com.jimmycasta.ditrading.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        return tradeRepository.findAllByOrderByIdTradeDesc(pageable);
    }

    @Override
    public int getTradesProfitCurrentMth(LocalDate startDate, LocalDate endDate) {
        return tradeRepository.getTradesProfitCurrentMth(startDate, endDate);
    }

    @Override
    public int getTradesLossCurrentMth(LocalDate startDate, LocalDate endDate) {
        return tradeRepository.getTradesLossCurrentMth(startDate, endDate);
    }

    @Override
    public int getAllTradesCurrentMth(LocalDate startDate, LocalDate endDate) {
        return tradeRepository.getAllTradesCurrentMth(startDate, endDate);
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

    @Override
    public List<String> getTopInstrumentCurrentMth() {
        return tradeRepository.getTopInstrumentCurrentMth();
    }

    @Override
    public List<String> getTopStrategiesCurrentMth() {
        return tradeRepository.getTopStrategiesCurrentMth();

    }

    @Override
    public double getLastBalance(LocalDate startDate, LocalDate endDate) {
        Optional<Double> optional = tradeRepository.getLastBalance(startDate, endDate);
        if (optional.isPresent()) {
            return optional.get();
        }
        return 0;
    }

    @Override
    public List<Boolean> getOpenAndCloseCurrentMth(LocalDate startDate, LocalDate endDate) {
        return tradeRepository.getOpenAndCloseCurrentMth(startDate, endDate);
    }
}

