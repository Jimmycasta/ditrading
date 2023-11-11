package com.jimmycasta.ditrading.repositories;

import com.jimmycasta.ditrading.entities.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusEntity,Integer> {
}
