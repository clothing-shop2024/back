package com.project.back.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
    
    @EmbeddedId
    private Integer id;

    @ManyToOne
    @MapsId("clothNumber")
    @JoinColumn(name = "cloth_number")
    private ClothEntity clothNumber;

    @ManyToOne
    @MapsId("sizeNumber")
    @JoinColumn(name = "size_number")
    private ClothSizeEntity sizeNumber;

}
