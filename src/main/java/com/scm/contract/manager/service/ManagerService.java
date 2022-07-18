package com.scm.contract.manager.service;

import com.scm.contract.commoninfo.entity.CommonInfoEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ManagerService {

    List<CommonInfoEntity> findContractListAll(String curActorId);
}
