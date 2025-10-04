package com.portpolio.MyPortpolio.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Embeddable
public class Experience {
    private String companyName;
    @Column(name = "description",columnDefinition = "text[]")
    private String[] description;
    private Date fromDate;
    private Date endDate;
    private String designation;
    private String place;
    private long designationId;
}
