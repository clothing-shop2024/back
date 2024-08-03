
package com.project.back.repository;

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
    UserEntity findByPassword(String Password);
    UserEntity findUserIdByUserEmail(String userEmail);
    UserEntity findByUserNameAndUserEmail(String userName, String userEmail);
}
  
