package com.project.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "clothColor")
@Table(name = "cloth_color")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClothColorEntity {
    
    @Id
    private int colorNumber;
    private String colorName;
    
}
