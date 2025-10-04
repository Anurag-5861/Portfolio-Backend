package com.portpolio.MyPortpolio.Repo;

import com.portpolio.MyPortpolio.DTO.StorageRequest;
import com.portpolio.MyPortpolio.Entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepo extends JpaRepository<Storage,Integer> {
}
