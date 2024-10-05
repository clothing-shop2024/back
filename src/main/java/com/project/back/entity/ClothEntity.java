package com.project.back.entity;

import jakarta.persistence.Entity;
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
    private String clothNumber;
    private String clothName;
    private String clothDate;
    private Integer stock;
    private Integer sizeNumber;
    private Integer colorNumber;
    private String clothDetailNumber;

}
