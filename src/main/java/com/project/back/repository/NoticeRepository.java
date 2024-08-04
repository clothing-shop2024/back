package com.project.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.back.entity.NoticeEntity;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Integer> {

    NoticeEntity findByNoticeNumber(Integer noticeNumber);

    NoticeEntity findByNoticeWriterId(String userId);

    List<NoticeEntity> findByOrderByNoticeNumberDesc();
    
}
