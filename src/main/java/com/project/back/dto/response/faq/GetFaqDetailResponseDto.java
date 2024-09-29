package com.project.back.dto.response.faq;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.common.util.ChangeDateFormatUtil;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.entity.FaqEntity;

import lombok.Getter;

@Getter
public class GetFaqDetailResponseDto extends ResponseDto {
    
    private Integer faqNumber;
    private String faqQuestion;
    private String faqAnswer;
    private String faqCategory;
    private String faqDate;
    private String faqWriterId;

    private GetFaqDetailResponseDto (FaqEntity faqEntities) throws Exception {

        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        String faqDate = ChangeDateFormatUtil.changeYYYYMMDD(faqEntities.getFaqDate());

        this.faqNumber = faqEntities.getFaqNumber();
        this.faqQuestion = faqEntities.getFaqQuestion();
        this.faqAnswer = faqEntities.getFaqAnswer();
        this.faqCategory = faqEntities.getFaqCategory();
        this.faqDate = faqDate;
        this.faqWriterId = faqEntities.getFaqWriterId();
    }

    public static ResponseEntity<GetFaqDetailResponseDto> success(FaqEntity faqEntity) throws Exception {

        GetFaqDetailResponseDto responseBody = new GetFaqDetailResponseDto(faqEntity);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        
    }
}
