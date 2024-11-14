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

@Entity(name = "favoriteCloth")
@Table(name = "favorite_cloth")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteClothEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 자동생성되서 데이터베이스에 추가 X
    private Integer favoriteNumber;

    private String userId;
    private String clothDetailNumber;
    private Integer clothNumber;

    public FavoriteClothEntity(String userId, Integer clothNumber) {
        this.userId = userId;
        this.clothNumber = clothNumber;
    }
}
