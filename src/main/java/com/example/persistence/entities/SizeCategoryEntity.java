package com.example.persistence.entities;

import com.example.persistence.enums.SizeCategoryEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "size_category")
public class SizeCategoryEntity {

    @Column(name = "size_category_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sizeCategoryID;

    @Column(name = "size_category_name")
    @Enumerated(EnumType.STRING)
    private SizeCategoryEnum category;

    @Column(name = "size_category_description")
    private String categoryDescription;

    public SizeCategoryEntity() {
    }


}