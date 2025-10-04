package com.portpolio.MyPortpolio.Service;


import com.portpolio.MyPortpolio.Entity.FriendDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FriendService {
    List<FriendDetails> getFriendDetails();
}
