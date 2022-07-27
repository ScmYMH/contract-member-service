package com.scm.contract.user.repository;

import com.scm.contract.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    
    @Query("SELECT tb.userNm FROM UserEntity tb WHERE tb.userId = :userId")
    Optional<String> findUserNameByUserId(@Param("userId") String userId);

    List<UserEntity> findAll();

    List<UserEntity> findByLoginId(String loginId);

    List<UserEntity>  findByUserNm(String userNm);
    List<UserEntity> findByLoginIdContainingAndUserNmContaining(String loginId, String userNm);
}
