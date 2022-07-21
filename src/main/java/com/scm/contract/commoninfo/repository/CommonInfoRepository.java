package com.scm.contract.commoninfo.repository;

import com.scm.contract.commoninfo.entity.CommonInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommonInfoRepository extends JpaRepository<CommonInfoEntity, String> {
    @Query("SELECT tb.cntrtNm FROM CommonInfoEntity tb WHERE tb.cntrtId = :cntrtId")
    Optional<String> findCntrtNameByCntrtId(@Param("cntrtId") String cntrtId);

    List<CommonInfoEntity> findByCrePersonId(String crePersonId);
}
