package com.scm.contract.manager.service;

import com.scm.contract.commoninfo.entity.CommonInfoEntity;
import com.scm.contract.manager.dto.ReqManagerChangeInfoPostDto;
import com.scm.contract.manager.dto.ReqManagerChangeInfoPutDto;
import com.scm.contract.manager.dto.ResManagerChangeInfoPostDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ManagerService {

    List<CommonInfoEntity> findContractListAll(String curActorId);

    List<ResManagerChangeInfoPostDto> insertManagerChangeInfo(ReqManagerChangeInfoPostDto mngChgInfoPostDto);

    boolean updateMangerChangeInfo(ReqManagerChangeInfoPutDto reqMngChgInfoPutDto);
}
