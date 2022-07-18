package com.scm.contract.manager.service;

import com.scm.contract.commoninfo.entity.CommonInfoEntity;
import com.scm.contract.commoninfo.repository.CommonInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public class ManagerServiceImpl {

    @Autowired
    CommonInfoRepository commonInfoRepository;

    public List<CommonInfoEntity> findContractListAll(String curActorId){
        return commonInfoRepository.findAll();
    }
}
