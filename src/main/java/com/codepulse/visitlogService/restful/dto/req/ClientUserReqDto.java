package com.codepulse.visitlogService.restful.dto.req;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class ClientUserReqDto {
    private Long id;
    private String userId;
    private String cltCd;
    private String email;
    private String level;
    private int useYn;
}
