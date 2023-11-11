package com.jimmycasta.ditrading.repositories;

import com.jimmycasta.ditrading.entities.StrategyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StrategyRepository extends JpaRepository<StrategyEntity,Integer> {
}
