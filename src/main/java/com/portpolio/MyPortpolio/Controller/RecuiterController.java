package com.portpolio.MyPortpolio.Controller;


import com.portpolio.MyPortpolio.Entity.RecuiterDetails;
import com.portpolio.MyPortpolio.Service.Impl.RecuiterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/my-portfolio/recuiter")
public class RecuiterController {
    @Autowired
    private final RecuiterServiceImpl recuiterService;

    public RecuiterController(RecuiterServiceImpl recuiterService) {
        this.recuiterService = recuiterService;
    }

    @GetMapping
    public ResponseEntity<List<RecuiterDetails>> getDetailsForRecuiter(){
        return ResponseEntity.ok(recuiterService.getRecuiterDetails());
    }
}
