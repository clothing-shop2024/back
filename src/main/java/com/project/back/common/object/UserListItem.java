package com.project.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.project.back.common.util.ChangeDateFormatUtil;
import com.project.back.entity.UserEntity;

import lombok.Getter;

@Getter
public class UserListItem {
    
    private String userId;
    private String userName;
    private String nickname;
    private String userEmail;
    private String joinDate;
    private String grade;
    private int points;

    private UserListItem(UserEntity userEntity) throws Exception {

        String joinDate = ChangeDateFormatUtil.changeYYYYMMDDHHMMSS(userEntity.getJoinDate());

        this.userId = userEntity.getUserId();
        this.userName = userEntity.getUserName();
        this.nickname = userEntity.getNickname();
        this.userEmail = userEntity.getUserEmail();
        this.joinDate = joinDate;
        this.grade = userEntity.getGrade();
        this.points = userEntity.getPoints();
    }

    public static List<UserListItem> getList(List<UserEntity> userEntities) throws Exception {

        List<UserListItem> userList = new ArrayList<>();

        for (UserEntity userEntity : userEntities) {
            UserListItem userListItem = new UserListItem(userEntity);
            userList.add(userListItem);
        }

        return userList;
    }
}
