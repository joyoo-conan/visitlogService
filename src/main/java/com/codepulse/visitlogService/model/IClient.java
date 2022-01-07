package com.codepulse.visitlogService.model;

public interface IClient {
    default String getCltCd() { return "empty"; }

    String getCltNm();
    String getCeo();
    String getTel();
    String getEmail();
    String getBizCd();
}
