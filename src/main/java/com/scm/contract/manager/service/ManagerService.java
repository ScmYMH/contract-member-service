package com.scm.contract.manager.service;

import com.scm.contract.commoninfo.entity.CommonInfoEntity;
import com.scm.contract.manager.dto.*;
import com.scm.contract.manager.entity.ManagerEntity;
import org.apache.catalina.Manager;

import java.util.List;
import java.util.stream.Stream;

public interface ManagerService {

    List<ResManagerChangInfoGetDto> findContractListByCrePersonId(String curActorId);

    List<ResManagerChangeInfoPostDto> insertManagerChangeInfo(ReqManagerChangeInfoPostDto mngChgInfoPostDto);

    boolean updateMangerChangeInfo(Integer[] seqNoArray);

    boolean deleteManagerChangeInfo(String seqNoParam);

    Stream<ManagerDto> getmember();

    Stream<ManagerDto> getmemberById(String loginId);

    Stream<ManagerDto> getmemberByName(String userNm);

    Stream<ManagerDto> getmemberByDelYn(String delYn);

    List<ManagerEntity> insertManager(List<ManagerEntity> managerEntity);
    
    String deleteManager(String userId);

    Stream<ManagerDto> getManagerList(String loginId, String userNm, String delYn);

}
