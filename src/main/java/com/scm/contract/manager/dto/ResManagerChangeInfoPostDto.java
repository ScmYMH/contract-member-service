package com.scm.contract.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ResManagerChangeInfoPostDto{
    private String cntrtId; // 계약 ID
    private String cntrtName; // 계약명
    private String preActorName; // 이전 계약담당자 ID로 이름 조회
    private String aftActorName; // 이후 계약담당자 ID로 이름 조회
    private String validDate; // 유효 시작일 -> format 변경 (20220714 -> 7/14/22)
    private String reasonDesc; // 변경사유 코멘트
    private String cmptYn; // 확정 여부

    public ResManagerChangeInfoPostDto(String cntrtId, String cntrtName, String preActorName, String aftActorName, String validDate, String reasonDesc, String cmptYn){
        this.setCntrtId(cntrtId);
        this.setCntrtName(cntrtName);
        this.setPreActorName(preActorName);
        this.setAftActorName(aftActorName);
        this.setValidDate(validDate);
        this.setReasonDesc(reasonDesc);
        if(cmptYn.equals("N")){
            this.setCmptYn("미확정");
        }else{
            this.setCmptYn("확정");
        }
    }
}
