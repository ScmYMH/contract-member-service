package com.scm.contract.manager.controller;

import com.scm.contract.commoninfo.entity.CommonInfoEntity;
import com.scm.contract.manager.dto.ManagerDto;
import com.scm.contract.manager.dto.ReqManagerChangeInfoPostDto;
import com.scm.contract.manager.dto.ReqManagerChangeInfoPutDeleteDto;
import com.scm.contract.manager.dto.ResManagerChangeInfoPostDto;
import com.scm.contract.manager.entity.ManagerEntity;
import com.scm.contract.manager.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequestMapping("contract/manager")
@Slf4j
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @GetMapping("/cntrtlist/{curActorId}")
    public List<CommonInfoEntity> getContractListByCurrentPersonId(@PathVariable String curActorId) {
        return managerService.findContractListAll(curActorId);
    }

    @PostMapping("/chginfo")
    public List<ResManagerChangeInfoPostDto> postManagerChangeInfo(@RequestBody ReqManagerChangeInfoPostDto mngChgInfoPostDto){
        return managerService.insertManagerChangeInfo(mngChgInfoPostDto);
    }

    @PutMapping("/chginfo")
    public boolean putManagerChangeInfo(@RequestBody ReqManagerChangeInfoPutDeleteDto mngChgInfoPutDto){
        return managerService.updateMangerChangeInfo(mngChgInfoPutDto);
    }

    //@RequestBody에 여러개의 계약ID값을 담아 보내기에는 restful하다고 생각하지 않아서 front단에서 하나씩 api를 보내느걸로 생각
    @DeleteMapping("/chginfo/{curActorId}")
    public boolean deleteManagerChangeInfo(@PathVariable String curActorId){
        return managerService.deleteManagerChangeInfo(curActorId);
    }
    
    @GetMapping("")
    public Stream<ManagerDto> show() {

        return managerService.getmember();
    }

    @GetMapping("/id/{loginId}")
    public Stream<ManagerDto> showbyId(@PathVariable String loginId) {

        return managerService.getmemberById(loginId);
    }

    @GetMapping("/name/{userNm}")
    public Stream<ManagerDto> showbyName(@PathVariable String userNm) {

        return managerService.getmemberByName(userNm);
    }

    @PostMapping("")
    public List<ManagerEntity> putManager(@RequestBody Map<String,List<ManagerEntity>> mapManagerEntity) {

        List<ManagerEntity> managerEntity=mapManagerEntity.get("data");

        return managerService.insertManager(managerEntity);

    }

    @DeleteMapping("/{userId}")
    public String delete(@PathVariable String userId) {

        return managerService.deleteManager(userId);

    }

}
