package com.scm.contract.manager.controller;

import com.scm.contract.commoninfo.entity.CommonInfoEntity;
import com.scm.contract.manager.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
