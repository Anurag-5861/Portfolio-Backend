package com.portpolio.MyPortpolio.Service;

import com.portpolio.MyPortpolio.Entity.RecuiterDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecuiterService {
    List<RecuiterDetails> getRecuiterDetails();
}
