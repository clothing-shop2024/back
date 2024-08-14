package com.project.back.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class FindIdResponseDto extends ResponseDto {

    private String userId;

    private FindIdResponseDto(String userId) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userId = userId;
    }

    public static ResponseEntity<FindIdResponseDto> success(String userId) {
        FindIdResponseDto responseBody = new FindIdResponseDto(userId);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}