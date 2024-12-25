package com.project.back.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// import com.project.back.dto.request.clothStock.PostClothStockRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "clothStock")
@Table(name = "cloth_stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClothStockEntity {

    @Id
    private Integer stockId;
    private Integer clothSizeNumber;
    private Integer clothColorNumber;
    private Integer quantity;
    private String stockDate;

    // public ClothStockEntity(PostClothStockRequestDto dto) {
    //     LocalDateTime now = LocalDateTime.now();
    //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    //     String stockDate = now.format(formatter);

    //     this.sizeNumber = dto.getSizeNumber();
    //     this.colorNumber = dto.getColorNumber();
    //     this.quantity = dto.getQuantity();
    //     this.clothId = dto.getClothId();
    //     this.stockDate = stockDate;
    // }

}
