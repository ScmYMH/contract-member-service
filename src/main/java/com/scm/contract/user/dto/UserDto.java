package com.scm.contract.user.dto;

import lombok.Data;

@Data
public class UserDto {

    private String loginId;
    private String userNm;
    private String email;
    private String employeeNumber;
    private String deptNm;
    private String insDate;
    private String updDate; // 삭제일
    private String delYn;

}
