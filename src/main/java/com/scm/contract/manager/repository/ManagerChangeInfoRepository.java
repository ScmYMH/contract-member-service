package com.scm.contract.manager.repository;

import com.scm.contract.manager.entity.ManagerChangeInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ManagerChangeInfoRepository extends JpaRepository<ManagerChangeInfoEntity, Integer> { // T: Table, ID: primary key type

}
