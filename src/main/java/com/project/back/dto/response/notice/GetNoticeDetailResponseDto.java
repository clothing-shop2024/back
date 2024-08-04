package com.project.back.dto.response.notice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.common.util.ChangeDateFormatUtil;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.entity.NoticeEntity;

import lombok.Getter;

@Getter
public class GetNoticeDetailResponseDto extends ResponseDto {

    private Integer noticeNumber;
    private String noticeTitle;
    private String noticeContents;
    private String noticeWriterId;
    private String noticeDate;
    private Integer viewCount;
    private String noticeImageUrl;

    private GetNoticeDetailResponseDto (NoticeEntity noticeEntity) throws Exception {

        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        String noticeDate = ChangeDateFormatUtil.changeYYYYMMDD(noticeEntity.getNoticeDate());

        this.noticeNumber = noticeEntity.getNoticeNumber();
        this.noticeTitle = noticeEntity.getNoticeTitle();
        this.noticeContents = noticeEntity.getNoticeContents();
        this.noticeWriterId = noticeEntity.getNoticeWriterId();
        this.noticeDate = noticeDate;
        this.viewCount = noticeEntity.getViewCount();
        this.noticeImageUrl = noticeEntity.getNoticeImageUrl();

    }

    public static ResponseEntity<GetNoticeDetailResponseDto> success(NoticeEntity noticeEntity) throws Exception {

        GetNoticeDetailResponseDto responseBody = new GetNoticeDetailResponseDto(noticeEntity);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);

    }
    
}
