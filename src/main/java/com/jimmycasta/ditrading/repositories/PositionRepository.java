package com.jimmycasta.ditrading.repositories;

import com.jimmycasta.ditrading.entities.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<PositionEntity,Integer> {
}
