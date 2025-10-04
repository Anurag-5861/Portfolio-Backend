package com.portpolio.MyPortpolio.Repo;

import com.portpolio.MyPortpolio.Entity.FriendDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepo extends JpaRepository<FriendDetails,Long> {
}
