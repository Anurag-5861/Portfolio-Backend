package com.portpolio.MyPortpolio.Entity;

import com.portpolio.MyPortpolio.Model.EducationEntry;
import jakarta.persistence.*;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class FriendDetails {

    @Id
    @Column(name = "id")
    private int id=1;

    @Column(name = "name")
    private String name;

    @Column(name = "about", columnDefinition = "TEXT")
    private String about;

    @ElementCollection
    @CollectionTable(
            name = "friend_details_education",
            joinColumns = @JoinColumn(name = "friend_details_id")
    )
    private List<EducationEntry> education = new ArrayList<>();

    @Column(name = "address")
    private String address;

    @Column(name = "facebook_id")
    private String facebookId;

    @Column(name = "instagram_id")
    private String instagramId;
}
