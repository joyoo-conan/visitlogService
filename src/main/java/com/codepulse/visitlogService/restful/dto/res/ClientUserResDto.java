package com.codepulse.visitlogService.restful.dto.res;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class ClientUserResDto {
    private Long id;
    private String userId;
    private String cltCd;
    private String email;
    private String level;
    private int useYn;
}
