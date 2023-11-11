package com.jimmycasta.ditrading.implement;

import com.jimmycasta.ditrading.entities.TraderEntity;
import com.jimmycasta.ditrading.repositories.TraderRepository;
import com.jimmycasta.ditrading.services.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TraderServiceImpl implements TraderService {

    private final TraderRepository traderRepository;

    @Autowired
    public TraderServiceImpl(TraderRepository traderRepository) {
        this.traderRepository = traderRepository;
    }

    @Override
    public Page<TraderEntity> getAll(Pageable pageable) {
        return traderRepository.findAll(pageable);
    }

    @Override
    public TraderEntity getById(int id) {
        Optional<TraderEntity> optional = traderRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public TraderEntity save(TraderEntity trader) {
        return traderRepository.save(trader);
    }

    @Override
    public void delete(int id) {
        traderRepository.deleteById(id);

    }
}
