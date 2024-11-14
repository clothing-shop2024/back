package com.project.back.dto.response.user;

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
public class GetMyQnaListResponseDto extends ResponseDto {

    private List<QnaListItem> qnaList;
    
    private GetMyQnaListResponseDto (List<QnaEntity> qnaEntities) throws Exception {

        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.qnaList = QnaListItem.getList(qnaEntities);

    }

    public static ResponseEntity<GetMyQnaListResponseDto> success (List<QnaEntity> qnaEntities) throws Exception {

        GetMyQnaListResponseDto responseBody = new GetMyQnaListResponseDto(qnaEntities);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        
    }
    
}
