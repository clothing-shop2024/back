package com.project.back.dto.response.user;

import lombok.Getter;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.back.common.object.UserListItem;
import com.project.back.dto.response.ResponseCode;
import com.project.back.dto.response.ResponseDto;
import com.project.back.dto.response.ResponseMessage;
import com.project.back.entity.UserEntity;

@Getter
public class GetAdminUserListResponseDto extends ResponseDto {
    
    private List<UserListItem> userList;

    private GetAdminUserListResponseDto (List<UserEntity> UserEntities) throws Exception {
        
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.userList = UserListItem.getList(UserEntities);
    }

    public static ResponseEntity<GetAdminUserListResponseDto> success(List<UserEntity> UserEntities) throws Exception {

        GetAdminUserListResponseDto responseBody = new GetAdminUserListResponseDto(UserEntities);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
