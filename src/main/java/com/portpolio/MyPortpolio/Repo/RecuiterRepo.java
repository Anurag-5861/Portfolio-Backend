package com.portpolio.MyPortpolio.Repo;

import com.portpolio.MyPortpolio.Entity.RecuiterDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecuiterRepo extends JpaRepository<RecuiterDetails,Long> {
}
