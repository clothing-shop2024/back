package com.project.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.back.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    boolean existsByUserId(String userId);

    boolean existsByNickname(String nickname);

    boolean existsByUserEmail(String userEmail);

    boolean existsByUserIdAndUserEmail(String userId, String userEmail);

    boolean existsByUserNameAndUserEmail(String userName, String userEmail);

    boolean existsByUserIdAndUserNameAndUserEmail(String userId, String userName, String userEmail);

    UserEntity findByUserId(String userId);

    UserEntity findByNickname(String nickname);

    UserEntity findUserPwByUserId(String userId);

    UserEntity findByUserEmail(String userEmail);

    UserEntity findByPassword(String password);

    UserEntity findUserIdByUserEmail(String userEmail);

    UserEntity findByUserNameAndUserEmail(String userName, String userEmail);

    UserEntity findByUserIdAndUserNameAndUserEmail(String userId, String userName, String userEmail);

    // board에 사용하려고
    UserEntity findUserRoleByUserId(String userId);

    // 관리자페이지 회원관리 리스트
    List<UserEntity> findByOrderByJoinDateDesc();
    List<UserEntity> findByOrderByJoinDateAsc();

    List<UserEntity> findByUserIdContainsOrderByJoinDateDesc(String searchWord);
    List<UserEntity> findByUserNameContainsOrderByJoinDateDesc(String searchWord);
    List<UserEntity> findByGradeContainsOrderByJoinDateDesc(String searchWord);
}