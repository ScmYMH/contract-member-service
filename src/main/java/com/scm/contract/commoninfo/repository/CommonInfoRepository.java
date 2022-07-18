package com.scm.contract.commoninfo.repository;

import com.scm.contract.commoninfo.entity.CommonInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonInfoRepository extends JpaRepository<CommonInfoEntity, String> {

}
