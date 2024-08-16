package com.project.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.back.entity.QnaEntity;

@Repository
public interface QnaRepository extends JpaRepository<QnaEntity, Integer> {

    QnaEntity findByQnaNumber(Integer qnaNumber);

    List<QnaEntity> findByOrderByQnaNumberDesc();

    List<QnaEntity>findByQnaWriterIdOrderByQnaNumberDesc(String qnaWriterId);

    List<QnaEntity> findByQnaWriterIdContainsOrderByQnaNumberDesc(String qnaWriterId);
    
}
