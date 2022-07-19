package com.scm.contract.manager.controller;

import com.scm.contract.commoninfo.entity.CommonInfoEntity;
import com.scm.contract.manager.dto.ReqManagerChangeInfoPostDto;
import com.scm.contract.manager.dto.ReqManagerChangeInfoPutDto;
import com.scm.contract.manager.dto.ResManagerChangeInfoPostDto;
import com.scm.contract.manager.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contract/manager")
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
    public boolean putManagerChangeInfo(@RequestBody ReqManagerChangeInfoPutDto mngChgInfoPutDto){
        return managerService.updateMangerChangeInfo(mngChgInfoPutDto);
    }

}
