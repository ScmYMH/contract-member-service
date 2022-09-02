package com.scm.contract.manager.repository;

import com.scm.contract.manager.entity.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ManagerRepository extends JpaRepository<ManagerEntity, String> {

    List<ManagerEntity> findAll();
    List<ManagerEntity> findByLoginId(String loginId);
    List<ManagerEntity> findByUserNm(String userNm);
    List<ManagerEntity> findByDelYn(String delYn);
    ManagerEntity findByUserIdAndDelYn(String userId, String delYn);
    List<ManagerEntity> findByLoginIdContainingAndUserNmContainingAndDelYnContaining(String loginId, String userNm, String delYn);

}
