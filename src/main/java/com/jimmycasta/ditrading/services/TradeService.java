package com.jimmycasta.ditrading.services;

import com.jimmycasta.ditrading.entities.TradeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;


public interface TradeService {

    List<TradeEntity> getAll();

    Page<TradeEntity> getAllPage(Pageable pageable);

    int getTradesProfitCurrentMth(LocalDate startDate, LocalDate endDate);

    int getTradesLossCurrentMth(LocalDate startDate, LocalDate endDate);

    int getAllTradesCurrentMth(LocalDate startDate, LocalDate endDate);

    TradeEntity getById(int id);

    TradeEntity save(TradeEntity trade);

    void delete(int id);

    List<String> getTopInstrumentCurrentMth();

    List<String> getTopStrategiesCurrentMth();

    double getLastBalance(LocalDate startDate, LocalDate endDate);

    List<Integer> getOpenAndCloseCurrentMth(LocalDate startDate, LocalDate endDate);


}
