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
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_visit_log")
@DynamicInsert
@DynamicUpdate
@Schema(description = "Client 방명록 테이블")
public class VisitLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 6)
    private long id;

    @Column(name = "clt_cd", nullable = false, length = 6)
    private String cltCd;

    @Column(name = "mobile", nullable = false, length = 45)
    private String mobile;

    @Column(name = "visit_nm", nullable = false, length = 120)
    @Schema(description = "방문자명")
    private String visitNm;

    @Column(name = "manager", nullable = false, length = 120)
    @Schema(description = "담당자")
    private String manager;

    @Column(name = "visit_clt", nullable = false, length = 120)
    @Schema(description = "방문업체")
    private String visitClt;

    @Column(name = "visitReason", nullable = false, length = 120)
    @Schema(description = "방문사유")
    private String visitReason;

    @Column(name = "location", nullable = false, length = 120)
    @Schema(description = "지역")
    private String location;

    @Column(name = "privacy_agree", nullable = false)
    @Schema(description = "개인정보 제공 허용여부(0:허용안함, 1:허용)")
    private int privacyAgree;

    @Column(name = "visit_date", nullable = false, length = 8)
    @Schema(description = "방문날짜(yyyyMMdd)")
    private String visitDate;

    @Column(name = "visit_time", nullable = false, length = 8)
    private String visitTime;

    @Column(name = "visit_dt", nullable = false)
    private LocalDateTime visitDt;
}
