package com.scm.contract.manager.dto;

import lombok.Data;

@Data
public class ResManagerChangInfoGetDto {
    private String cntrtId; // 계약 ID
    private String cntrtNm; // 계약명
    private String cntrtScd; // // 계약 상태코드(상태명)
    private String cntrtStartDate; // 계약 시작 일
    private String cntrtEndDate; // 계약 종료 일



    public ResManagerChangInfoGetDto(String cntrtId, String cntrtNm, String cntrtScd, String cntrtStartDate, String cntrtEndDate) {
        this.setCntrtId(cntrtId);
        this.setCntrtNm(cntrtNm);
        this.setCntrtScd(cntrtScd);
        this.setCntrtStartDate(cntrtStartDate);
        this.setCntrtEndDate(cntrtEndDate);
    }
}