package com.scm.contract.manager.service;

import com.scm.contract.commoninfo.entity.CommonInfoEntity;
import com.scm.contract.manager.dto.ReqManagerChangeInfoPostDto;
import com.scm.contract.manager.dto.ReqManagerChangeInfoPutDeleteDto;
import com.scm.contract.manager.dto.ResManagerChangeInfoPostDto;
import com.scm.contract.manager.dto.ManagerDto;
import com.scm.contract.manager.entity.ManagerEntity;

import java.util.List;
import java.util.stream.Stream;

public interface ManagerService {

    List<CommonInfoEntity> findContractListByCrePersonId(String curActorId);

    List<ResManagerChangeInfoPostDto> insertManagerChangeInfo(ReqManagerChangeInfoPostDto mngChgInfoPostDto);

    boolean updateMangerChangeInfo(ReqManagerChangeInfoPutDeleteDto reqMngChgInfoPutDto);

    boolean deleteManagerChangeInfo(String curActorId);

    Stream<ManagerDto> getmember();

    Stream<ManagerDto> getmemberById(String loginId);

    Stream<ManagerDto> getmemberByName(String userNm);

    List<ManagerEntity> insertManager(List<ManagerEntity> managerEntity);
    
    String deleteManager(String userId);

}
