package com.jimmycasta.ditrading.repositories;

import com.jimmycasta.ditrading.entities.SymbolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymbolRepository extends JpaRepository<SymbolEntity, Integer> {
}
