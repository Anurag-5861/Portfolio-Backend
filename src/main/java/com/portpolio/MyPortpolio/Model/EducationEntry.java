package com.portpolio.MyPortpolio.Model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Embeddable
@Data
public class EducationEntry {
    private String institution;
    private String degree;
    private long startYear;
    private long endYear;
    private double percentage;
}
