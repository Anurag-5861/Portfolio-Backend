package com.portpolio.MyPortpolio.Model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class Projects {
    private long project_id;
    private String projectNames;
    private String projectUrls;
}
