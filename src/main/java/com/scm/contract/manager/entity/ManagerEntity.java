package com.scm.contract.manager.entity;

import lombok.Builder;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "TB_CNTRT_REP", schema = "tcnt")
public class ManagerEntity {

    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "LOGIN_ID")
    private String loginId;

    @Column(name = "USER_NM")
    private String userNm;

    private String email;

    @Column(name = "EMPLOYEE_NUMBER")
    private String employeeNumber;

    @Column(name = "DEPT_NM")
    private String deptNm;

    @Column(name = "DEL_YN")
    private String delYn;

    @Column(name = "INS_PERSON_ID")
    private String insPersonId;

    @Column(name = "INS_DATE")
    private String insDate;

    @Column(name = "INS_TIME")
    private String insTime;

    @Column(name = "UPD_PERSON_ID")
    private String updPersonId;

    @Column(name = "UPD_DATE")
    private String updDate;

    @Column(name = "UPD_TIME")
    private String updTime;

}
