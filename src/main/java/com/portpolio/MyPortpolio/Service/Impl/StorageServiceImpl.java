package com.portpolio.MyPortpolio.Service.Impl;

import com.portpolio.MyPortpolio.DTO.StorageRequest;
import com.portpolio.MyPortpolio.Entity.Storage;
import com.portpolio.MyPortpolio.Repo.StorageRepo;
import com.portpolio.MyPortpolio.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageRepo storageRepo;

    @Override
    public StorageRequest getStorageDetails() {
        Optional<Storage> storageOpt = storageRepo.findById(1);
        StorageRequest dto = new StorageRequest();

        if (storageOpt.isPresent()) {
            Storage storage = storageOpt.get();
            dto.setResume_url(storage.getResume());
        }

        return dto;
    }
}
