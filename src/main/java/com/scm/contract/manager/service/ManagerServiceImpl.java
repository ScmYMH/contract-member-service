package com.scm.contract.manager.service;

import com.scm.contract.codedefinition.repository.CodeDefinitionRepository;
import com.scm.contract.commoninfo.entity.CommonInfoEntity;
import com.scm.contract.config.SecurityService;
import com.scm.contract.manager.dto.*;
import com.scm.contract.manager.repository.ManagerRepository;
import com.scm.contract.commoninfo.repository.CommonInfoRepository;
import com.scm.contract.manager.entity.ManagerChangeInfoEntity;
import com.scm.contract.manager.entity.ManagerEntity;
import com.scm.contract.manager.repository.ManagerChangeInfoRepository;
import com.scm.contract.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

@Service
@Slf4j
public class ManagerServiceImpl implements ManagerService{

    @Autowired
    ManagerRepository contractRepository;


    @Autowired
    CommonInfoRepository commonInfoRepository;

    @Autowired
    ManagerChangeInfoRepository managerChangeInfoRepository;

    @Autowired
    CodeDefinitionRepository codeDefinitionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SecurityService securityService;

    Date today;

    public List<ResManagerChangInfoGetDto> findContractListByCrePersonId(String crePersonId){
        List<ResManagerChangInfoGetDto> resmciGetList = new ArrayList<>();

        List<CommonInfoEntity> commonInfoEntityList = commonInfoRepository.findByCrePersonId(crePersonId);

        for(int i = 0; i < commonInfoEntityList.size(); i++){
            if(commonInfoEntityList.get(i).getDelYn().equals("Y")) continue;

            String cntrtScd = codeDefinitionRepository.findCdVMeaningByCdV(commonInfoEntityList.get(i).getCntrtScd()).get(); // 코드값 -> 코드 의미 가져오기

            ResManagerChangInfoGetDto rmcigd = new ResManagerChangInfoGetDto(
                    commonInfoEntityList.get(i).getCntrtId(),
                    commonInfoEntityList.get(i).getCntrtNm(),
                    cntrtScd,
                    commonInfoEntityList.get(i).getCntrtStartDate(),
                    commonInfoEntityList.get(i).getCntrtEndDate()
            );
            resmciGetList.add(rmcigd);
        }

        resmciGetList.sort(new Comparator<ResManagerChangInfoGetDto>() {
            @Override
            public int compare(ResManagerChangInfoGetDto o1, ResManagerChangInfoGetDto o2) {
                return Integer.parseInt(o2.getCntrtEndDate()) - Integer.parseInt(o1.getCntrtEndDate());
            }
        });

        return resmciGetList;

    }

    public List<ResManagerChangeInfoPostDto> insertManagerChangeInfo(ReqManagerChangeInfoPostDto reqMngChgInfoPostDto){

        String[] cntrtArray = reqMngChgInfoPostDto.getCntrtId();

        today = new Date();
        String userId = securityService.getIdAtToken();

        List<ResManagerChangeInfoPostDto> resmcipdList = new ArrayList<>();

        for(int i = 0 ; i < cntrtArray.length; i++){
            ManagerChangeInfoEntity managerChangeInfoEntity = ManagerChangeInfoEntity.builder()
                    .cntrtId(cntrtArray[i])
                    .preActorId(reqMngChgInfoPostDto.getPreActorId())
                    .aftActorId(reqMngChgInfoPostDto.getAftActorId())
                    .validDate(reqMngChgInfoPostDto.getValidDate())
                    .reasonDesc(reqMngChgInfoPostDto.getReasonDesc())
                    .cmptYn("N")
                    .insDate(new SimpleDateFormat("yyyyMMdd").format(today))
                    .insTime(new SimpleDateFormat("HHmmss").format(today))
                    .insPersonId(userId) // 로그인 한 사용자의 id값(token에서 꺼내온 값)
                    .updDate(new SimpleDateFormat("yyyyMMdd").format(today))
                    .updTime(new SimpleDateFormat("HHmmss").format(today))
                    .updPersonId(userId) // 로그인 한 사용자의 id값(token에서 꺼내온 값)
                    .build();
            managerChangeInfoEntity = managerChangeInfoRepository.save(managerChangeInfoEntity);
            log.info(String.valueOf(managerChangeInfoEntity));
            // 계약 ID 값으로 계약명 불러오기
            Optional<String> optCntrtName = commonInfoRepository.findCntrtNameByCntrtId(cntrtArray[i]);
            String cntrtName = null;
            if(optCntrtName.isPresent()) cntrtName = optCntrtName.get();

            // 현담당자 ID 값으로 현담당자 name 불러오기
            Optional<String> optPreActorName = userRepository.findUserNameByUserId(managerChangeInfoEntity.getPreActorId());
            String preActorName = null;
            if(optCntrtName.isPresent()) preActorName = optPreActorName.get();

            // 인수담당자 ID 값으로 인수담당자 name 불러오기
            Optional<String> optAftActorName = userRepository.findUserNameByUserId(managerChangeInfoEntity.getAftActorId());
            String aftActorName = null;
            if(optCntrtName.isPresent()) aftActorName = optAftActorName.get();

            if(managerChangeInfoEntity.getCntrtId() != null){
                resmcipdList.add( new ResManagerChangeInfoPostDto(
                                managerChangeInfoEntity.getSeqNo(),
                                managerChangeInfoEntity.getCntrtId(),
                                cntrtName, preActorName, aftActorName,
                                managerChangeInfoEntity.getValidDate(),
                                managerChangeInfoEntity.getReasonDesc(),
                                managerChangeInfoEntity.getCmptYn()));
                continue;
            }
            else return null;
        }

        return resmcipdList;
    }

    public boolean updateMangerChangeInfo(Integer[] seqNoArray){

        today = new Date();
        String userId = securityService.getIdAtToken();

        for(int i = 0; i < seqNoArray.length; i++){
            // TB_CNTRT_CHG_INFO 테이블 확정여부(CMPT_YN) 항목 'Y'로 UPDATE
            Optional<ManagerChangeInfoEntity> optManagerChangeInfo=managerChangeInfoRepository.findById(seqNoArray[i]);
            optManagerChangeInfo.ifPresent(mngChgInfo->{
                mngChgInfo.setCmptYn("Y");
                mngChgInfo.setCmptDate(new SimpleDateFormat("yyyyMMdd").format(today));
                mngChgInfo.setCmptTime(new SimpleDateFormat("HHmmss").format(today));
                mngChgInfo.setUpdDate(new SimpleDateFormat("yyyyMMdd").format(today));
                mngChgInfo.setUpdTime(new SimpleDateFormat("HHmmss").format(today));
                mngChgInfo.setUpdPersonId(userId); // 로그인 한 사용자의 id값(token에서 꺼내온 값)
                managerChangeInfoRepository.save(mngChgInfo);
            });

            // TB_CNTRT_INFO 테이블 계약ID(CNTRT_ID)의 계약 담당자 ID(CRE_PERSON_ID) 변경
            Optional<ManagerChangeInfoEntity> optMngChgInfoEntity = managerChangeInfoRepository.findById(seqNoArray[i]);
            String aftActorId = optMngChgInfoEntity.get().getAftActorId(); // 계약 ID 값으로 바뀐 계약 담당자 ID(AFT_ACTOR_ID) 가져오기
            Optional<CommonInfoEntity> optCommonInfo = commonInfoRepository.findById(optMngChgInfoEntity.get().getCntrtId());
            optCommonInfo.ifPresent(commonInfo->{
                commonInfo.setCrePersonId(aftActorId);
                commonInfo.setUpdDate(new SimpleDateFormat("yyyyMMdd").format(today));
                commonInfo.setUpdTime(new SimpleDateFormat("HHmmss").format(today));
                commonInfo.setUpdPersonId(userId); // 로그인 한 사용자의 id값(token에서 꺼내온 값)
                commonInfoRepository.save(commonInfo);
            });
        }

        return true;
    }

    // 확정여부 validation -> front에서 체크할거기 때문에 확정여부가 Y인지 체크할 필요 없음
    public boolean deleteManagerChangeInfo(String seqNoParam){
        String[] seqNoArray = seqNoParam.split(",");
        for(int i = 0; i < seqNoArray.length; i++){
            Optional<ManagerChangeInfoEntity> optManagerChangeInfoEntity = managerChangeInfoRepository.findById(Integer.parseInt(seqNoArray[i]));
            if(optManagerChangeInfoEntity.isPresent()){
                managerChangeInfoRepository.deleteById(optManagerChangeInfoEntity.get().getSeqNo());
            }else{
                return false;
            }
        }
        return true;
    }


    @Override
    public Stream<ManagerDto> getmember() {

        List<ManagerEntity> findAllManagerEntity = contractRepository.findAll();

        Stream<ManagerDto> managerData = findAllManagerEntity.stream().map(data -> {

            ManagerDto managerDto = new ManagerDto();

            managerDto.setUserId(data.getUserId());
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

            managerDto.setUserId(data.getUserId());
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

            managerDto.setUserId(data.getUserId());
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
    public Stream<ManagerDto> getmemberByDelYn(String delYn) {

        List<ManagerEntity> findAllManagerEntity = contractRepository.findByDelYn(delYn);


        Stream<ManagerDto> managerDataByDel = findAllManagerEntity.stream().map(data -> {

            ManagerDto managerDto = new ManagerDto();

            managerDto.setUserId(data.getUserId());
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

        return managerDataByDel;
    }

    @Override
    public Stream<ManagerDto> getManagerList(String loginId, String userNm , String delYn) {
        List<ManagerEntity> findAllManagerInfo = contractRepository.findByLoginIdContainingAndUserNmContainingAndDelYnContaining(loginId, userNm, delYn);
        Stream<ManagerDto> managerData = findAllManagerInfo.stream().map(data -> {

            ManagerDto managerDto = new ManagerDto();

            managerDto.setUserId(data.getUserId());
            managerDto.setUserNm(data.getUserNm());
            managerDto.setDelYn(data.getDelYn());
            managerDto.setEmail(data.getEmail());
            managerDto.setDeptNm(data.getDeptNm());
            managerDto.setEmployeeNumber(data.getEmployeeNumber());
            managerDto.setInsDate(data.getInsDate());
            managerDto.setLoginId(data.getLoginId());
            managerDto.setUpdDate(data.getUpdDate());
            managerDto.setUserId(data.getUserId());

            return managerDto;
        });
        return managerData;
    }

    @Override
    public List<ManagerEntity> insertManager(List<ManagerEntity> managerEntity) {

        Date today = new Date();
        String userId = securityService.getIdAtToken();

        managerEntity.forEach(managerEntity1 -> managerEntity1.setInsDate(new SimpleDateFormat("yyyyMMdd").format(today).toString()));
        managerEntity.forEach(managerEntity1 -> managerEntity1.setInsTime(new SimpleDateFormat("HHmmss").format(today).toString()));
        managerEntity.forEach(managerEntity1 -> managerEntity1.setUpdPersonId(userId));
        managerEntity.forEach(managerEntity1 -> managerEntity1.setDelYn("N"));
        managerEntity.forEach(managerEntity1 -> managerEntity1.setInsPersonId("Admin"));

        return contractRepository.saveAll(managerEntity);

    }
    @Override
    public String deleteManager(String userId) {
        String isSuccess = "N";
        ManagerEntity managerEntity = contractRepository.findByUserIdAndDelYn(userId, "N");

        Date today = new Date();

        managerEntity.setUpdDate(new SimpleDateFormat("yyyyMMdd").format(today).toString());
        managerEntity.setUpdTime(new SimpleDateFormat("HHmmss").format(today).toString());
        managerEntity.setUpdPersonId(userId);

        if(managerEntity == null) {
            return isSuccess;
        }
        managerEntity.setDelYn("Y");
        isSuccess = String.valueOf(contractRepository.save(managerEntity) != null);

        return isSuccess;
    }

}
