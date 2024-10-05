package com.project.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "clothSize")
@Table(name = "cloth_size")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClothSizeEntity {

    @Id
    private int sizeNumber;
    private String sizeName;
    
}
