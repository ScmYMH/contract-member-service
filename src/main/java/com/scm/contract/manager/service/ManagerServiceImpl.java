package com.scm.contract.manager.service;

import com.scm.contract.manager.dto.ManagerDto;
import com.scm.contract.manager.entity.ManagerEntity;
import com.scm.contract.manager.repository.ManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerRepository contractRepository;

    @Override
    public Stream<ManagerDto> getmember() {

        List<ManagerEntity> findAllManagerEntity = contractRepository.findAll();

        Stream<ManagerDto> managerData = findAllManagerEntity.stream().map(data -> {

            ManagerDto managerDto = new ManagerDto();

            managerDto.setUserNm(data.getUserNm());
            managerDto.setDelYn(data.getDelYn());
            managerDto.setEmail(data.getEmail());
            managerDto.setDeptNm(data.getDeptNm());
            managerDto.setEmployeeNumber(data.getEmployeeNumber());
            managerDto.setInsDate(data.getInsDate());
            managerDto.setLoginId(data.getLoginId());
            managerDto.setUpdDate(data.getUpdDate());

            return managerDto;
        });

        return managerData;
    }

    @Override
    public Stream<ManagerDto> getmemberById(String loginId) {

        List<ManagerEntity> findAllManagerEntity = contractRepository.findByLoginId(loginId);


        Stream<ManagerDto> managerDataById = findAllManagerEntity.stream().map(data -> {

            ManagerDto managerDto = new ManagerDto();

            managerDto.setUserNm(data.getUserNm());
            managerDto.setDelYn(data.getDelYn());
            managerDto.setEmail(data.getEmail());
            managerDto.setDeptNm(data.getDeptNm());
            managerDto.setEmployeeNumber(data.getEmployeeNumber());
            managerDto.setInsDate(data.getInsDate());
            managerDto.setLoginId(data.getLoginId());
            managerDto.setUpdDate(data.getUpdDate());

            return managerDto;
        });

        return managerDataById;
    }

    @Override
    public Stream<ManagerDto> getmemberByName(String userNm) {

        List<ManagerEntity> findAllManagerEntity = contractRepository.findByUserNm(userNm);


        Stream<ManagerDto> managerDataByName = findAllManagerEntity.stream().map(data -> {

            ManagerDto managerDto = new ManagerDto();

            managerDto.setUserNm(data.getUserNm());
            managerDto.setDelYn(data.getDelYn());
            managerDto.setEmail(data.getEmail());
            managerDto.setDeptNm(data.getDeptNm());
            managerDto.setEmployeeNumber(data.getEmployeeNumber());
            managerDto.setInsDate(data.getInsDate());
            managerDto.setLoginId(data.getLoginId());
            managerDto.setUpdDate(data.getUpdDate());

            return managerDto;
        });

        return managerDataByName;
    }

    @Override
    public List<ManagerEntity> insertManager(List<ManagerEntity> managerEntity) {

        Date today = new Date();

        managerEntity.forEach(managerEntity1 -> managerEntity1.setUpdDate(new SimpleDateFormat("yyyyMMdd").format(today).toString()));
        managerEntity.forEach(managerEntity1 -> managerEntity1.setUpdTime(new SimpleDateFormat("HHmmss").format(today).toString()));
        managerEntity.forEach(managerEntity1 -> managerEntity1.setInsDate(new SimpleDateFormat("yyyyMMdd").format(today).toString()));
        managerEntity.forEach(managerEntity1 -> managerEntity1.setInsTime(new SimpleDateFormat("HHmmss").format(today).toString()));
        managerEntity.forEach(managerEntity1 -> managerEntity1.setUpdPersonId("202207130002"));
        managerEntity.forEach(managerEntity1 -> managerEntity1.setDelYn("N"));

        return contractRepository.saveAll(managerEntity);

    }

    @Override
    public String deleteManager(String userId) {
        String isSuccess = "N";
        ManagerEntity managerEntity = contractRepository.findByUserIdAndDelYn(userId, "N");

        Date today = new Date();

        managerEntity.setUpdDate(new SimpleDateFormat("yyyyMMdd").format(today).toString());
        managerEntity.setUpdTime(new SimpleDateFormat("HHmmss").format(today).toString());
        managerEntity.setUpdPersonId("202207130002");

        if(managerEntity == null) {
            return isSuccess;
        }
        managerEntity.setDelYn("Y");
        isSuccess = String.valueOf(contractRepository.save(managerEntity) != null);

        return isSuccess;
    }


}
