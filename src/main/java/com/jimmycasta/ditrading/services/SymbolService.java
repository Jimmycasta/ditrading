package com.jimmycasta.ditrading.services;

import com.jimmycasta.ditrading.entities.SymbolEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SymbolService {

    Page<SymbolEntity> getAllPage(Pageable pageable);

    List<SymbolEntity>getAll();

    SymbolEntity save(SymbolEntity symbol);

    SymbolEntity getById(int id);

    void delete(int id);
}
