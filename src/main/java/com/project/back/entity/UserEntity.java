package com.project.back.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.Instant;

import com.project.back.dto.request.auth.FindPasswordResetRequestDto;
import com.project.back.dto.request.auth.SignUpRequestDto;
import com.project.back.dto.request.user.PatchUserInfoRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="user")
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    private String userId;
    private String password;
    private String userName;
    private String nickname;
    private String userEmail;
    private String userBirthDay;
    private String userRole;  
    private String joinPath;
    private String joinDate;  

    public UserEntity(SignUpRequestDto dto) {
        
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String joinDate = simpleDateFormat.format(now);
        
        this.userId = dto.getUserId();
        this.password = dto.getPassword();
        this.userName = dto.getUserName();
        this.nickname = dto.getNickname();
        this.userEmail = dto.getUserEmail();
        // this.userAddress = dto.getUserAddress();
        this.userBirthDay = dto.getUserBirthDay();
        this.joinPath = "HOME";
        this.joinDate = joinDate;
    }

    public void update(PatchUserInfoRequestDto dto) {
        this.password = dto.getPassword();
        this.userName = dto.getUserName();
        this.nickname = dto.getNickname();
        this.userEmail = dto.getUserEmail();
        // this.userAddress = dto.getUserAddress();
        this.userBirthDay = dto.getUserBirthDay();
    }

    public void findPassword(FindPasswordResetRequestDto dto) {
        this.password = dto.getPassword();
    }
    
}