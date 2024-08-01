package com.project.back.dto.response.faq;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.common.object.FaqListItem;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.entity.FaqEntity;

import lombok.Getter;

@Getter
public class GetFaqListResponseDto extends ResponseDto {

    private List<FaqListItem> faqList;
    
    private GetFaqListResponseDto(List<FaqEntity> FaqEntities) throws Exception {

        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.faqList = FaqListItem.getFaqList(FaqEntities);
    }

    public static ResponseEntity<GetFaqListResponseDto> success(List<FaqEntity> FaqEntities) throws Exception {

        GetFaqListResponseDto responseBody = new GetFaqListResponseDto(FaqEntities);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
