package com.scm.contract.manager.controller;

import com.scm.contract.manager.dto.ManagerDto;
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
