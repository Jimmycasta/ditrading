package com.jimmycasta.ditrading.repositories;

import com.jimmycasta.ditrading.entities.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<TradeEntity,Integer> {
}
