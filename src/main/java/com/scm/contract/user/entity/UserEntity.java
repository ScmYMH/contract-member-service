package com.scm.contract.user.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="TB_USER_INFO", schema = "tcom")
public class UserEntity {
    @Id
    @Column(name = "USER_ID")
    private String userId; // 시스템사용자 ID

    @Column(name="LOGIN_ID")
    private String loginId; // 로그인 ID

    @Column(name="LOGIN_PW")
    private String loginPw; // 로그인비밀번호

    @Column(name="PW_CHG_CYCLE")
    private String pwChgCycle; //비밀번호변경주기

    @Column(name="PW_INS_ERR_CNT")
    private String pwInsErrCnt; // 비밀번호입력오류회수

    @Column(name="USER_NM")
    private String userNm; // 시스템사용자명

    @Column(name="TEL_NO")
    private String telNo; // 전화번호

    @Column(name="HP_NO")
    private String hpNo; // 핸드폰번호

    private String email; // 이메일

    @Column(name="EMPLOYEE_NUMBER")
    private String employeeNumber; // 직번

    @Column(name="DEPT_NM")
    private String deptNm; // 부서명

    @Column(name="LANG_CD")
    private String langCd; // 언어코드

    @Column(name="NATION_CD")
    private String nationCd; // 국가코드

    @Column(name="DEL_YN")
    private String delYn; // 삭제여부

    @Column(name="INS_PERSON_ID")
    private String insPersonId; // 입력자 ID

    @Column(name="INS_DATE")
    private String insDate; // 입력일자

    @Column(name="INS_TIME")
    private String insTime; // 입력시각

    @Column(name="UPD_PERSON_ID")
    private String updPersonId; // 수정자 ID

    @Column(name="UPD_DATE")
    private String updDate; // 수정일자

    @Column(name="UPD_TIME")
    private String updTime; // 수정시각
}