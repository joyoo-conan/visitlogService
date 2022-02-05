package com.codepulse.visitlogService.restful.dto.req;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class ClientReqDto {
    private String cltCd;
    private String cltNm;
    private String ceo;
    private String tel;
    private String email;
    private String bizCd;
    private int useYn;
}
