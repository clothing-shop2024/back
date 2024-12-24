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

@Entity(name = "cloth")
@Table(name = "cloth")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClothEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String clothId;
    private Integer clothNumber;
    private String clothName;
    private String category1;
    private String category2;
    private String clothFeatures;
    private Integer price;
    private Integer discountPrice;
    private Integer clothImageNumber;
    private String clothDate;
    private Integer viewCount;
    private Double ratingAvg;
    private Integer reviewCount;
    private Integer favoriteCount;

    
    // private Integer favoriteNumber;
    
}
