package com.scm.contract.codedefinition.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_code_definition", schema="tcom")
@Data // @Getter/@Setter, @ToString 등 생성
public class CodeDefinitionEntity {
    @Id
    private Integer seq; // 일련번호
    @Column(name="cd_tp")
    private String cdTp; // 코드유형명
    @Column(name="cd_tp_meaning")
    private String cdTpMeaning; // 코드유형의미
    @Column(name="cd_v")
    private String cdV; // 코드값
    @Column(name="cd_v_meaning")
    private String cdVMeaning; // 코드값의미
    @Column(name="del_yn")
    private String delYn; // 삭제여부
    @Column(name="ins_date")
    private String insDate; // 입력일자
    @Column(name="ins_time")
    private String insTime; // 입력시간
    @Column(name="ins_person_id")
    private String insPersonId; //입력자ID
    @Column(name="upd_date")
    private String updDate; // 수정일자
    @Column(name="upd_time")
    private String updTime; // 수정시간
    @Column(name="upd_person_id")
    private String updPersonId; //수정자ID
}
