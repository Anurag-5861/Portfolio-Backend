package com.portpolio.MyPortpolio.Model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class SkillSet {
    private List<String> language;
    private List<String> frameWorks;
    private List<String> database;
    private List<String> tools;
    private List<String> architecture;
}
