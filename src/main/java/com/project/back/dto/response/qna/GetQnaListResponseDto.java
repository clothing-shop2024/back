package com.project.back.dto.response.qna;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.common.object.QnaListItem;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.entity.QnaEntity;

import lombok.Getter;

@Getter
public class GetQnaListResponseDto extends ResponseDto {

    private List<QnaListItem> qnaList;

    private GetQnaListResponseDto (List<QnaEntity> qnaEntities) throws Exception {
        
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.qnaList = QnaListItem.getList(qnaEntities);

    }

    public static ResponseEntity<GetQnaListResponseDto> success(List<QnaEntity> qnaEntities) throws Exception {

        GetQnaListResponseDto responseBody = new GetQnaListResponseDto(qnaEntities);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);

    }
    
}
