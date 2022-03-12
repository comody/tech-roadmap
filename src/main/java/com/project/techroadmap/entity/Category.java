package com.project.techroadmap.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "categoryName"})
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "category_id")
    private Category parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> children = new ArrayList<Category>();

    @OneToMany(mappedBy = "category")
    private List<Material> materials = new ArrayList<Material>();

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    @Builder
    public Category(String categoryName, Category parent) {
        this.categoryName = categoryName;
        this.parent = parent;
    }
}
