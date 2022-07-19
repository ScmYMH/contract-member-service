package com.scm.contract.manager.service;

import com.scm.contract.commoninfo.entity.CommonInfoEntity;
import com.scm.contract.commoninfo.repository.CommonInfoRepository;
import com.scm.contract.manager.dto.ReqManagerChangeInfoPostDto;
import com.scm.contract.manager.dto.ReqManagerChangeInfoPutDto;
import com.scm.contract.manager.dto.ResManagerChangeInfoPostDto;
import com.scm.contract.manager.entity.ManagerChangeInfoEntity;
import com.scm.contract.manager.repository.ManagerChangeInfoRepository;
import com.scm.contract.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ManagerServiceImpl implements ManagerService{

    @Autowired
    CommonInfoRepository commonInfoRepository;

    @Autowired
    ManagerChangeInfoRepository managerChangeInfoRepository;

    @Autowired
    UserRepository userRepository;

    public List<CommonInfoEntity> findContractListAll(String curActorId){
        return commonInfoRepository.findAll();
    }

    public List<ResManagerChangeInfoPostDto> insertManagerChangeInfo(ReqManagerChangeInfoPostDto reqMngChgInfoPostDto){
        String[] cntrtArray = reqMngChgInfoPostDto.getCntrtId();

        Date today = new Date();

        List<ResManagerChangeInfoPostDto> resmcipdList = new ArrayList<>();

        for(int i = 0 ; i < cntrtArray.length; i++){
            ManagerChangeInfoEntity managerChangeInfoEntity = ManagerChangeInfoEntity.builder()
                    .cntrtId(cntrtArray[i])
                    .preActorId(reqMngChgInfoPostDto.getPreActorId())
                    .aftActorId(reqMngChgInfoPostDto.getAftActorId())
                    .validDate(reqMngChgInfoPostDto.getValidDate())
                    .reasonDesc(reqMngChgInfoPostDto.getReasonDesc())
                    .cmptYn("N")
                    .delYn("N")
                    .insDate(new SimpleDateFormat("yyyyMMdd").format(today))
                    .insTime(new SimpleDateFormat("HHmmss").format(today))
                    .insPersonId("202207130004") // 원래는 로그인 한 사용자의 id값(token에서 꺼내오면 될듯)
                    .build();

            managerChangeInfoEntity = managerChangeInfoRepository.save(managerChangeInfoEntity);

            // 계약 ID 값으로 계약명 불러오기
            Optional<String> optCntrtName = commonInfoRepository.findCntrtNameByCntrtId(cntrtArray[i]);
            System.out.println("optCntrName : " + optCntrtName);
            String cntrtName = null;
            if(optCntrtName.isPresent()) cntrtName = optCntrtName.get();

            // 현담당자 ID 값으로 현담당자 name 불러오기
            Optional<String> optPreActorName = userRepository.findUserNameByUserId(managerChangeInfoEntity.getPreActorId());
            System.out.println("optPreActorName : " + optPreActorName);
            String preActorName = null;
            if(optCntrtName.isPresent()) preActorName = optPreActorName.get();

            // 인수담당자 ID 값으로 인수담당자 name 불러오기
            Optional<String> optAftActorName = userRepository.findUserNameByUserId(managerChangeInfoEntity.getAftActorId());
            System.out.println("optAftActorName : " + optAftActorName);
            String aftActorName = null;
            if(optCntrtName.isPresent()) aftActorName = optAftActorName.get();

            if(managerChangeInfoEntity.getCntrtId() != null){
                resmcipdList.add( new ResManagerChangeInfoPostDto(
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

    public boolean updateMangerChangeInfo(ReqManagerChangeInfoPutDto reqMngChgInfoPutDto){
        String[] cntrtArray = reqMngChgInfoPutDto.getCntrtId();

        for(int i = 0; i < cntrtArray.length; i++){
            // TB_CNTRT_CHG_INFO 테이블 확정여부(CMPT_YN) 항목 'Y'로 UPDATE
            Optional<ManagerChangeInfoEntity> optManagerChangeInfo=managerChangeInfoRepository.findById(cntrtArray[i]);
            optManagerChangeInfo.ifPresent(mngChgInfo->{
                mngChgInfo.setCmptYn("Y");
                managerChangeInfoRepository.save(mngChgInfo);
            });

            // TB_CNTRT_INFO 테이블 계약ID(CNTRT_ID)의 계약 담당자 ID(CRE_PERSON_ID) 변경
            String aftActorId = managerChangeInfoRepository.findAftActorIdByCntrtId(cntrtArray[i]).get(); // 계약 ID 값으로 바뀐 계약 담당자 ID(AFT_ACTOR_ID) 가져오기
            Optional<CommonInfoEntity> optCommonInfo = commonInfoRepository.findById(cntrtArray[i]);
            optCommonInfo.ifPresent(commonInfo->{
                commonInfo.setCrePersonId(aftActorId);
                commonInfoRepository.save(commonInfo);
            });
        }

        return true;
    }

}
