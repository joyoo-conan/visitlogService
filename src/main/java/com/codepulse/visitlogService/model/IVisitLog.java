package com.codepulse.visitlogService.model;

public interface IVisitLog {
    default long getId() { return 0; }

    String getCltCd();
    String getMobile();
    String getVisitNm();
    String getManager();
    String getVisitClt();
    String getVisitReason();
    String getLocation();
    int getPrivacyAgree();
}
