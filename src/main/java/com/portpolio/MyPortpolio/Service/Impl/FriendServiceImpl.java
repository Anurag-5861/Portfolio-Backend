package com.portpolio.MyPortpolio.Service.Impl;

import com.portpolio.MyPortpolio.Entity.FriendDetails;
import com.portpolio.MyPortpolio.Repo.FriendRepo;
import com.portpolio.MyPortpolio.Service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private final FriendRepo friendRepo;

    public FriendServiceImpl(FriendRepo friendRepo) {
        this.friendRepo = friendRepo;
    }

    @Override
    public List<FriendDetails> getFriendDetails() {
        return friendRepo.findAll();
    }
}
