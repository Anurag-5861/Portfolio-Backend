package com.portpolio.MyPortpolio.Controller;

import com.portpolio.MyPortpolio.Entity.FriendDetails;
import com.portpolio.MyPortpolio.Service.Impl.FriendServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/my-portfolio/friend")
public class FriendController {
    @Autowired
    private final FriendServiceImpl friendService;

    public FriendController(FriendServiceImpl friendService) {
        this.friendService = friendService;
    }

    @GetMapping
    public ResponseEntity<List<FriendDetails>> getDetailsForFriends(){
        return ResponseEntity.ok(friendService.getFriendDetails());
    }
}