package com.project.back.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetMyInfoResponseDto extends ResponseDto {
    
    private String userId;
    private String userName;
    private String nickname;
    private String userEmail;
    private String userRole;
    private String joinPath;
    private String joinDate;

     private GetMyInfoResponseDto(UserEntity userEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.userId = userEntity.getUserId();
        this.userName = userEntity.getUserName();
        this.nickname = userEntity.getNickname();
        this.userEmail = userEntity.getUserEmail();
        this.userRole = userEntity.getUserRole();
        this.joinPath = userEntity.getJoinPath();
        this.joinDate = userEntity.getJoinDate();
    }

    public static ResponseEntity<GetMyInfoResponseDto> success(UserEntity userEntity) {
        GetMyInfoResponseDto responseBody = new GetMyInfoResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
