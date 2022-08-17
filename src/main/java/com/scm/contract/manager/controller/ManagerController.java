package com.scm.contract.manager.controller;

import com.scm.contract.commoninfo.entity.CommonInfoEntity;
import com.scm.contract.manager.dto.*;
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

    @GetMapping("/cntrtlist/{crePersonId}")
    public List<ResManagerChangInfoGetDto> getContractListByCrePersonId(@PathVariable String crePersonId) {

        return managerService.findContractListByCrePersonId(crePersonId);
    }

    @PostMapping("/chginfo")
    public List<ResManagerChangeInfoPostDto> postManagerChangeInfo(@RequestBody ReqManagerChangeInfoPostDto mngChgInfoPostDto){

        return managerService.insertManagerChangeInfo(mngChgInfoPostDto);
    }

    @PutMapping("/chginfo")
    public boolean putManagerChangeInfo(@RequestBody Integer[] seqNoArray){

        return managerService.updateMangerChangeInfo(seqNoArray);
    }

    // @RequestBody에 여러개의 계약ID값을 담아 보내기에는 restful하다고 생각하지 않아서 front단에서 하나씩 api를 보내는걸로 생각
    // 계약 ID값과 인수담당자 이름을 확인해서 삭제
    @DeleteMapping("/chginfo/{seqNoParam}")
    public boolean deleteManagerChangeInfo(@PathVariable String seqNoParam){

        return managerService.deleteManagerChangeInfo(seqNoParam);
    }
    


    @GetMapping("/id/{loginId}")
    public Stream<ManagerDto> showbyId(@PathVariable String loginId) {

        return managerService.getmemberById(loginId);
    }

    @GetMapping("/name/{userNm}")
    public Stream<ManagerDto> showbyName(@PathVariable String userNm) {

        return managerService.getmemberByName(userNm);
    }

    @GetMapping("/del/{delYn}")
    public Stream<ManagerDto> showbyDel(@PathVariable String delYn) {

        return managerService.getmemberByDelYn(delYn);
    }

    @GetMapping("/search")
    public Stream<ManagerDto> showManagerInfo(@RequestParam String loginId, @RequestParam String userNm, @RequestParam String delYn) {

            return managerService.getManagerList(loginId, userNm, delYn);
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
