package com.project.techroadmap.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "materialName"})
public class Material {
    @Id
    @GeneratedValue
    @Column(name = "material_id")
    private Long id;

    private String materialName;
    private String url;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    @Builder
    public Material(String materialName, String url, Category category) {
        this.materialName = materialName;
        this.url = url;

        if (category != null) {
            addMaterial(category);
        }
    }

    private void addMaterial(Category category) {
        this.category = category;
        category.getMaterials().add(this);
    }
}
