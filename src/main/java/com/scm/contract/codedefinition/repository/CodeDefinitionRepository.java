package com.scm.contract.codedefinition.repository;

import com.scm.contract.codedefinition.entity.CodeDefinitionEntity;
import com.scm.contract.commoninfo.entity.CommonInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeDefinitionRepository extends JpaRepository<CodeDefinitionEntity, Integer> {

    @Query("SELECT tb.cdVMeaning FROM CodeDefinitionEntity tb WHERE tb.cdV = :cdV")
    Optional<String> findCdVMeaningByCdV(@Param("cdV") String cdV);
}
