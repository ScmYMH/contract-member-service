package com.scm.contract.manager.dto;

import lombok.Data;

@Data
public class ReqManagerChangeInfoPostDto {
    private String[] cntrtId; // 계약 ID List
    private String preActorId; // 이전 계약담당자 ID
    private String aftActorId; // 이후 계약담당자 ID
    private String validDate; // 유효 시작일
    private String reasonDesc; // 변경사유 코멘트
}
