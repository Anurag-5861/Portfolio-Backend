package com.portpolio.MyPortpolio.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Storage {
    @Id
    private int id=1;
    @Column(name = "resume_url", columnDefinition = "TEXT")
    private String resume;
}
