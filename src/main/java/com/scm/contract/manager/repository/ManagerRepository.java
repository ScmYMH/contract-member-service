package com.scm.contract.manager.repository;

import com.scm.contract.manager.entity.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<ManagerEntity, String> {
}
