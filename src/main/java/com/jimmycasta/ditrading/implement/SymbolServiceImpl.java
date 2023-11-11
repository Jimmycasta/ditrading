package com.jimmycasta.ditrading.implement;

import com.jimmycasta.ditrading.entities.SymbolEntity;
import com.jimmycasta.ditrading.repositories.SymbolRepository;
import com.jimmycasta.ditrading.services.SymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SymbolServiceImpl implements SymbolService {

    private final SymbolRepository symbolRepository;

    @Autowired
    public SymbolServiceImpl(SymbolRepository symbolRepository) {
        this.symbolRepository = symbolRepository;

    }

    @Override
    public Page<SymbolEntity> getAllPage(Pageable pageable) {
        return symbolRepository.findAll(pageable);
    }

    @Override
    public List<SymbolEntity> getAll() {
        return symbolRepository.findAll();
    }

    @Override
    public SymbolEntity save(SymbolEntity symbol) {

        return symbolRepository.save(symbol);
    }

    @Override
    public SymbolEntity getById(int id) {
        Optional<SymbolEntity> optional = symbolRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }


    @Override
    public void delete(int id) {
        symbolRepository.deleteById(id);

    }
}
