package com.scm.contract.user.dto;

import lombok.Data;

@Data
public class UserDto {

    private String userId;
    private String loginId;
    private String userNm;
    private String telNo;
    private String hpNo;
    private String email;
    private String employeeNumber;
    private String deptNm;
    private String langCd;
    private String nationCd;
    private String insDate;
    private String updDate; // 삭제일
    private String delYn;

}
