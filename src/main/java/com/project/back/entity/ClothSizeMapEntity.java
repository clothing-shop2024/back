package com.project.back.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "clothSizeMap")
@Table(name = "cloth_size_map")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClothSizeMapEntity {
    
    @ManyToOne
    @Id
    @JoinColumn(name = "cloth_id")
    private ClothEntity clothId;

    @ManyToOne
    @Id
    @JoinColumn(name = "size_number")
    private ClothSizeEntity sizeNumber;

}
