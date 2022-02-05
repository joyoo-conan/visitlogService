package com.codepulse.visitlogService.restful.dto.res;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
public class VisitLogResDto {
    private Long id;
    private String cltCd;
    private String mobile;
    private String visitNm;
    private String manager;
    private String visitClt;
    private String visitReason;
    private String location;
    private int privacyAgree;
    private String visitDate;
    private String visitTime;
    private LocalDateTime visitDt;
}
