package com.portpolio.MyPortpolio.Service;

import com.portpolio.MyPortpolio.DTO.StorageRequest;
import org.springframework.stereotype.Service;

@Service
public interface StorageService {
    StorageRequest getStorageDetails();
}
