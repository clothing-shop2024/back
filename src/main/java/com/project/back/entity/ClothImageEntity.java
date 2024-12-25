package com.project.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "clothImage")
@Table(name = "cloth_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClothImageEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clothImageNumber;
    private String clothMainImage;
    private String clothImage1;
    private String clothImage2;
    private String clothImage3;
    private String clothImage4;
    private String clothImage5;
}
