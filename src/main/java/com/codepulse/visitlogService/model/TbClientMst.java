package com.codepulse.visitlogService.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString(callSuper = true)
@DynamicInsert
@DynamicUpdate
@Table(name = "tb_client_mst")
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TbClientMst {
    @Id
    @Column(name = "clt_cd", nullable = false, unique = true, length = 6)
    private String cltCd;

    @Column(name = "clt_nm", nullable = false, length = 250)
    private String cltNm;

    @Column(name = "ceo", nullable = false, length = 120)
    private String ceo;

    @Column(name = "tel", nullable = false, length = 45)
    private String tel;

    @Column(name = "email", nullable = false, length = 200)
    private String email;

    @Column(name = "biz_cd", nullable = false, length = 20)
    private String bizCd;

    @Column(name = "use_yn", nullable = false, columnDefinition = "tinyint")
    private int useYn;
}
