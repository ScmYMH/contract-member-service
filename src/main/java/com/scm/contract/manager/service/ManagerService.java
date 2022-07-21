package com.scm.contract.manager.service;

import com.scm.contract.commoninfo.entity.CommonInfoEntity;
import com.scm.contract.manager.dto.ReqManagerChangeInfoPostDto;
import com.scm.contract.manager.dto.ReqManagerChangeInfoPutDeleteDto;
import com.scm.contract.manager.dto.ResManagerChangeInfoPostDto;

import java.util.List;

public interface ManagerService {

    List<CommonInfoEntity> findContractListAll(String curActorId);

    List<ResManagerChangeInfoPostDto> insertManagerChangeInfo(ReqManagerChangeInfoPostDto mngChgInfoPostDto);

    boolean updateMangerChangeInfo(ReqManagerChangeInfoPutDeleteDto reqMngChgInfoPutDto);

    boolean deleteManagerChangeInfo(String curActorId);
}
