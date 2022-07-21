package com.scm.contract.manager.repository;

import com.scm.contract.manager.entity.ManagerChangeInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ManagerChangeInfoRepository extends JpaRepository<ManagerChangeInfoEntity, String> { // T: Table, ID: primary key type
    // 계약 ID 값으로 바뀐 계약 담당자 ID(AFT_ACTOR_ID) 가져오기
    @Query("SELECT tb.aftActorId FROM ManagerChangeInfoEntity tb WHERE tb.cntrtId = :cntrtId")

    Optional<String> findAftActorIdByCntrtId(@Param("cntrtId") String cntrtId);
}
