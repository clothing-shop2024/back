package com.project.back.dto.response.qna;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.common.util.ChangeDateFormatUtil;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.entity.QnaEntity;

import lombok.Getter;

@Getter
public class GetQnaDetailResponseDto extends ResponseDto {

    private Integer qnaNumber;
    private String qnaTitle;
    private String qnaContents;
    private String qnaWriterId;
    private String qnaDate;
    private Integer viewCount;
    private String qnaCategory;
    private boolean qnaPublic;
    private String qnaImageUrl;
    private String qnaComment;

    private GetQnaDetailResponseDto (QnaEntity qnaEntities) throws Exception {
        
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        String qnaDate = ChangeDateFormatUtil.changeYYYYMMDD(qnaEntities.getQnaDate());
        
        this.qnaNumber = qnaEntities.getQnaNumber();
        this.qnaTitle = qnaEntities.getQnaTitle();
        this.qnaContents = qnaEntities.getQnaContents();
        this.qnaWriterId = qnaEntities.getQnaWriterId();
        this.qnaDate = qnaDate;
        this.viewCount = qnaEntities.getViewCount();
        this.qnaImageUrl = qnaEntities.getQnaImageUrl();
        this.qnaPublic = qnaEntities.getQnaPublic();
        this.qnaCategory = qnaEntities.getQnaCategory();
        this.qnaComment = qnaEntities.getQnaComment();

    }


    public static ResponseEntity<GetQnaDetailResponseDto> success(QnaEntity qnaEntities) throws Exception {
        
        GetQnaDetailResponseDto responseBody = new GetQnaDetailResponseDto(qnaEntities);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);

    }
    
}
