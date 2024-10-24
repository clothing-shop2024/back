package com.project.back.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import com.project.back.dto.request.auth.FindPasswordResetRequestDto;
import com.project.back.dto.request.auth.SignUpRequestDto;
import com.project.back.dto.request.user.PatchUserInfoRequestDto;
import com.project.back.dto.request.user.PutEmailModifyRequestDto;
import com.project.back.dto.request.user.PutPasswordModifyRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@Table(name = "user")
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
    private String grade;
    private int points;

    public UserEntity(SignUpRequestDto dto) {

        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String joinDate = simpleDateFormat.format(now);

        this.userId = dto.getUserId();
        this.password = dto.getPassword();
        this.userName = dto.getUserName();
        this.nickname = dto.getNickname();
        this.userEmail = dto.getUserEmail();
        this.userBirthDay = dto.getUserBirthDay();
        this.userRole = "ROLE_USER";
        this.joinPath = "HOME";
        this.joinDate = joinDate;
        // 기본등급
        this.grade = "White";
        // 기본 포인트
        this.points = 0;

    }

    public UserEntity(String userId, String password, String userName, String nickname, String userEmail,
            String userRole, String joinPath, String grade, int points) {

        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String joinDate = simpleDateFormat.format(now);

        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.nickname = nickname;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.joinPath = joinPath;
        this.joinDate = joinDate;
        this.grade = grade;
        this.points = points;
    }

    public void update(PatchUserInfoRequestDto dto) {
        // this.password = dto.getPassword();
        this.userName = dto.getUserName();
        this.nickname = dto.getNickname();
        // this.userEmail = dto.getUserEmail();
        this.userBirthDay = dto.getUserBirthDay();
    }

    public void findPassword(FindPasswordResetRequestDto dto) {
        this.password = dto.getPassword();
    }

    public void findModify(PutPasswordModifyRequestDto dto) {
        this.password = dto.getPassword();
    }

    public void emailModify(PutEmailModifyRequestDto dto) {
        this.userEmail = dto.getUserEmail();
    }

    // 등급 업데이트 메서드
    public void updateGrade(String newGrade) {
        this.grade = newGrade;
    }

    // 포인트 증가 메서드
    public void addPoints(int addPoints) {
        if (addPoints < 0) {
            throw new IllegalArgumentException("포인트는 0보다 작을 수 없습니다.");
        }
        this.points += addPoints;
    }

    // 포인트 설정 메서드
    public void setPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("포인트는 0보다 작을 수 없습니다.");
        }
        this.points = points;
    }

}