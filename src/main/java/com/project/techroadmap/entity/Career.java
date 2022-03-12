package com.project.techroadmap.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "careerName"})
public class Career {
    @Id @GeneratedValue
    @Column(name = "career_id")
    private Long id;
    private String careerName;

    @OneToMany(mappedBy = "career")
    private List<Member> members = new ArrayList<Member>();

    @Builder
    public Career(String careerName) {
        this.careerName = careerName;
    }
}
