package com.project.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.back.entity.FaqEntity;

@Repository
public interface FaqRepository extends JpaRepository<FaqEntity, Integer> {
    
    FaqEntity findByFaqNumber(Integer faqNuber);

    List<FaqEntity> findByOrderByFaqNumberDesc();
}
