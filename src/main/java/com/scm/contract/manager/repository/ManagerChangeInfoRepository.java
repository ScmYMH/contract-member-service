package com.scm.contract.manager.repository;

import com.scm.contract.manager.entity.ManagerChangeInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ManagerChangeInfoRepository extends JpaRepository<ManagerChangeInfoEntity, String> { // T: Table, ID: primary key type

}
