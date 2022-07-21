package com.scm.contract.manager.controller;

import com.scm.contract.commoninfo.entity.CommonInfoEntity;
import com.scm.contract.manager.dto.ReqManagerChangeInfoPostDto;
import com.scm.contract.manager.dto.ReqManagerChangeInfoPutDeleteDto;
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
    public boolean putManagerChangeInfo(@RequestBody ReqManagerChangeInfoPutDeleteDto mngChgInfoPutDto){
        return managerService.updateMangerChangeInfo(mngChgInfoPutDto);
    }

    //@RequestBody에 여러개의 계약ID값을 담아 보내기에는 restful하다고 생각하지 않아서 front단에서 하나씩 api를 보내느걸로 생각
    @DeleteMapping("/chginfo/{curActorId}")
    public boolean deleteManagerChangeInfo(@PathVariable String curActorId){
        return managerService.deleteManagerChangeInfo(curActorId);
    }

}
