package com.project.back.dto.response.notice;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.common.object.NoticeListItem;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.entity.NoticeEntity;

import lombok.Getter;

@Getter
public class GetNoticeListResponseDto extends ResponseDto {

    private List<NoticeListItem> noticeList;

    private GetNoticeListResponseDto(List<NoticeEntity> NoticeEntities) throws Exception {

        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.noticeList = NoticeListItem.getList(NoticeEntities);

    }

    public static ResponseEntity<GetNoticeListResponseDto> success(List<NoticeEntity> NoticeEntities) throws Exception {

        GetNoticeListResponseDto responseBody = new GetNoticeListResponseDto(NoticeEntities);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);

    }
    
}
