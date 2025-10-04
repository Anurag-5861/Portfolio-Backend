package com.portpolio.MyPortpolio.Entity;


import com.portpolio.MyPortpolio.Model.EducationEntry;
import com.portpolio.MyPortpolio.Model.Experience;
import com.portpolio.MyPortpolio.Model.Projects;
import com.portpolio.MyPortpolio.Model.SkillSet;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class RecuiterDetails {
    @Id
    private int id = 1;
    @Column(name = "photo_url", columnDefinition = "TEXT")
    private String photoUrl;
    private String name;
    @Column(name = "about", columnDefinition = "TEXT")
    private String about;
    @ElementCollection
    private List<EducationEntry> education;
    @ElementCollection
    private List<Experience> experience;
    @ElementCollection
    private List<SkillSet> skillSet;
    @ElementCollection
    private List<Projects> projects;
    private String githubUrl;
    private String linkedinUrl;
    private String mailId;
    @Column(name = "resume_url", columnDefinition = "TEXT")
    private String resumeUrl;
}
