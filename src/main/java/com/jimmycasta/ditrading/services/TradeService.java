package com.jimmycasta.ditrading.services;

import com.jimmycasta.ditrading.entities.TradeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TradeService {

    List<TradeEntity> getAll();

    Page<TradeEntity> getAllPage(Pageable pageable);

    TradeEntity getById(int id);

    TradeEntity save(TradeEntity trade);

    void delete(int id);
}
