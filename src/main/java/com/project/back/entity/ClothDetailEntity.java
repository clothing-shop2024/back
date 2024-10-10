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

@Entity(name = "clothDetail")
@Table(name = "cloth_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClothDetailEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String clothDetailNumber;
    private String clothDetailName;
    private String clothCategory1;
    private String clothCategory2;
    private Integer price;
    private Integer discountPrice;
    private String clothDate;
    private Integer viewCount;
    private Integer reviewNumber;
    private Integer favoriteNumber;
    private Double ratingAvg;
    private String clothImage1;
    private String clothImage2;

    // 순번
    private Integer clothDetailSequence;

}
