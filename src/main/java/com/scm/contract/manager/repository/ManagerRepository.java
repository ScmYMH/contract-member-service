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
    ManagerEntity findByUserIdAndDelYn(String userId, String delYn);

}
