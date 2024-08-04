package com.project.back.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import com.project.back.dto.request.notice.PostNoticeRequestDto;
import com.project.back.dto.request.notice.PutNoticeRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="notice")
@Table(name="notice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noticeNumber;
    private String noticeTitle;
    private String noticeContents;
    private String noticeWriterId;
    private String noticeDate;
    private Integer viewCount;
    private String noticeImageUrl;

    public NoticeEntity(PostNoticeRequestDto dto, String userId) {

        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String noticeDate = simpleDateFormat.format(now);

        this.noticeTitle = dto.getNoticeTitle();
        this.noticeContents = dto.getNoticeContents();
        this.noticeWriterId = userId;
        this.noticeDate = noticeDate;
        this.viewCount = 0;
        this.noticeImageUrl = dto.getNoticeImageUrl();

    }

    // 조회수 증가
    public void increaseViewCount() {
        this.viewCount++;
    }

    // 게시물 수정
    public void update(PutNoticeRequestDto dto) {
        this.noticeTitle = dto.getNoticeTitle();
        this.noticeContents = dto.getNoticeContents();
        this.noticeImageUrl = dto.getNoticeImageUrl();
    }
    
}
