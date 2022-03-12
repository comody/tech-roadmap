package com.project.techroadmap.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username"})
public class Member {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String password;
    private String username;
    private int age;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="career_id")
    private Career career;

    public Member(String username, int age, Career career) {
        this.username = username;
        this.age = age;

        if (career != null) {
            changeCareer(career);
        }
    }

    @Builder
    public Member(String username, String password, int age, Career career, Role role) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.role = role;

        if (career != null) {
            changeCareer(career);
        }
    }

    public void changeCareer(Career career) {
        this.career = career;
        career.getMembers().add(this);
    }
}
