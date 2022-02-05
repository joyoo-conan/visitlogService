package com.codepulse.visitlogService.restful.model;

import com.codepulse.visitlogService.restful.model.base.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_client_mst")
@DynamicInsert
@DynamicUpdate
@Schema(description = "Client 마스터 테이블")
public class Client extends BaseTimeEntity {
    @Id
    @Column(name = "clt_cd", nullable = false, unique = true, length = 6)
    @Schema(description = "고객사 코드")
    private String cltCd;

    @Column(name = "clt_nm", nullable = false, length = 250)
    @Schema(description = "고객사명")
    private String cltNm;

    @Column(name = "ceo", nullable = false, length = 120)
    @Schema(description = "고객사 대표자")
    private String ceo;

    @Column(name = "tel", nullable = false, length = 45)
    @Schema(description = "고객사 전화번호")
    private String tel;

    @Column(name = "email", nullable = false, length = 200)
    @Schema(description = "고객사 email")
    private String email;

    @Column(name = "biz_cd", nullable = false, length = 20)
    @Schema(description = "고객사 사업자코드")
    private String bizCd;

    @Column(name = "use_yn", nullable = false, columnDefinition = "tinyint")
    @Schema(description = "사용여부")
    private Integer useYn;

    @PrePersist
    public void prePersist() {
        this.useYn = this.useYn == null ? 1 : this.useYn;
    }
}
