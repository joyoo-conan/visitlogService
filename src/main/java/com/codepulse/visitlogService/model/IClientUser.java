package com.codepulse.visitlogService.model;

public interface IClientUser {
    default long getId() { return 0; }

    String getUserId();
    String getCltCd();
    String getEmail();
    int getLevel();
}
