package com.jimmycasta.ditrading.implement;

import com.jimmycasta.ditrading.entities.PnLResultEntity;
import com.jimmycasta.ditrading.repositories.PnLResultRepository;
import com.jimmycasta.ditrading.services.PnLResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PnLResultServiceImpl implements PnLResultService {

    private final PnLResultRepository pnLResultRepository;

    @Autowired
    public PnLResultServiceImpl(PnLResultRepository pnLResultRepository) {
        this.pnLResultRepository = pnLResultRepository;
    }

    @Override
    public List<PnLResultEntity> getAll() {
        return pnLResultRepository.findAll();
    }

    @Override
    public PnLResultEntity save(PnLResultEntity status) {
        return pnLResultRepository.save(status);
    }

    @Override
    public void delete(int id) {
        pnLResultRepository.deleteById(id);

    }
}
