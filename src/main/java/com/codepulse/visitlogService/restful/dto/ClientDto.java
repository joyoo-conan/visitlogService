package com.codepulse.visitlogService.restful.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {
    private String cltCd;
    private String cltNm;
    private String ceo;
    private String tel;
    private String email;
    private String bizCd;
    private int useYn;
}
