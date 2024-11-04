package com.project.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.back.entity.ClothEntity;

@Repository
public interface ClothInfoRepository extends JpaRepository<ClothEntity, String> {

    // 상품 ID로 상품 조회
    ClothEntity findByClothNumber(String clothNumber);

    // 재고 수량으로 조회
    List<ClothEntity> findByStockGreaterThan(Integer stock);

    // 특정 색상 번호로 조회
    List<ClothEntity> findByColorNumber(Integer colorNumber);

    // 특정 사이즈 번호로 조회
    List<ClothEntity> findBySizeNumber(Integer sizeNumber);

    // 특정 색상 번호와 사이즈 번호로 조회 (있어야 하는지 애매..)
    // List<ClothEntity> findByClothColorNumberAndSizeNumber(Integer colorNumber,
    // Integer sizeNumber);
}
