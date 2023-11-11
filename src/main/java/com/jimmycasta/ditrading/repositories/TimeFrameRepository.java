package com.jimmycasta.ditrading.repositories;

import com.jimmycasta.ditrading.entities.TimeFrameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeFrameRepository extends JpaRepository<TimeFrameEntity,Integer> {
}
