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

@Entity(name = "clothColorMap")
@Table(name = "cloth_color_map")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClothColorMapEntity {

    @ManyToOne
    @Id
    @JoinColumn(name = "cloth_number")
    private ClothEntity clothNumber;

    @ManyToOne
    @Id
    @JoinColumn(name = "color_number")
    private ClothColorEntity colorNumber;

}
