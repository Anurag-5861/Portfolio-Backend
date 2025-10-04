package com.portpolio.MyPortpolio.Service.Impl;

import com.portpolio.MyPortpolio.Entity.RecuiterDetails;
import com.portpolio.MyPortpolio.Repo.RecuiterRepo;
import com.portpolio.MyPortpolio.Service.RecuiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecuiterServiceImpl implements RecuiterService {

    @Autowired
    private final RecuiterRepo recuiterRepo;

    public RecuiterServiceImpl(RecuiterRepo recuiterRepo) {
        this.recuiterRepo = recuiterRepo;
    }

    @Override
    public List<RecuiterDetails> getRecuiterDetails() {
        return recuiterRepo.findAll();
    }
}
