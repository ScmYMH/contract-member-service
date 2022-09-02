package com.scm.contract.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;

@Entity
@Table(name="tb_cntrt_chg_info", schema="tcms",
        uniqueConstraints = @UniqueConstraint(columnNames={"cntrt_id", "seq_no"}))
@Data // @Getter/@Setter, @ToString 등 생성
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerChangeInfoEntity {

    @Column(name="cntrt_id")
    private String cntrtId; //계약 ID

    @Id
    @GeneratedValue
    @Column(name="seq_no")
    private Integer seqNo;

    @Column(name="pre_actor_id")
    private String preActorId; // 이전 계약담당자 ID

    @Column(name="aft_actor_id")
    private String aftActorId; // 이후 계약담당자 ID

    @Column(name="valid_date")
    private String validDate; // 유효 시작일

    @Column(name="reason_desc")
    private String reasonDesc; // 변경사유 코멘트

    @Column(name="cmpt_yn")
    private String cmptYn; // 확정 여부

    @Column(name="cmpt_date")
    private String cmptDate;

    @Column(name="cmpt_time")
    private String cmptTime;

    @Column(name="ins_date")
    private String insDate;

    @Column(name="ins_time")
    private String insTime;

    @Column(name="ins_person_id")
    private String insPersonId;

    @Column(name="upd_date")
    private String updDate;

    @Column(name="upd_time")
    private String updTime;

    @Column(name="upd_person_id")
    private String updPersonId;

}
