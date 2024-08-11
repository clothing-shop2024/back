package com.project.back.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.project.back.dto.request.qna.PostQnaRequestDto;
import com.project.back.dto.request.qna.PutQnaRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "qna")
@Table(name = "qna")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QnaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer qnaNumber;
    private String qnaWriterId;
    private boolean status;
    private boolean qnaPublic = true;
    private String qnaTitle;
    private String qnaContents;
    private String qnaDate;
    private String qnaComment;
    private Integer viewCount;
    private String qnaCategory;
    private String qnaImageUrl;

    public QnaEntity(PostQnaRequestDto dto, String userId) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        String qnaDate = now.format(formatter);

        this.status = false;
        this.qnaTitle = dto.getQnaTitle();
        this.qnaContents = dto.getQnaContents();
        this.qnaWriterId = userId;
        this.qnaDate = qnaDate;
        this.viewCount = 0;
        this.qnaCategory = dto.getQnaCategory();
        this.qnaPublic = dto.isQnaPublic();
        this.qnaImageUrl = dto.getQnaImageUrl();

    }

    // 조회수 증가
    public void increaseViewCount() {
        this.viewCount++;
    }

    // 게시물 수정
    public void update(PutQnaRequestDto dto) {

        this.qnaTitle = dto.getQnaTitle();
        this.qnaContents = dto.getQnaContents();
        this.qnaCategory = dto.getQnaCategory();
        this.qnaImageUrl = dto.getQnaImageUrl();
        this.qnaPublic = dto.isQnaPublic();

    }
}
