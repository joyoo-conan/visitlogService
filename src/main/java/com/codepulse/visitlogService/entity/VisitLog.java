package com.codepulse.visitlogService.Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "tb_visit_log")
@Entity
public class VisitLog {
    @Id
    @Column(name = "id", nullable = false, unique = true, length = 6)
    private long id;

    @Column(name = "clt_cd", nullable = false, length = 6)
    private String cltCd;

    @Column(name = "mobile", nullable = false, length = 45)
    private String mobile;

    @Column(name = "visit_nm", nullable = false, length = 120)
    private String visitNm;

    @Column(name = "manager", nullable = false, length = 120)
    private String manager;

    @Column(name = "visit_clt", nullable = false, length = 120)
    private String visitClt;

    @Column(name = "visitReason", nullable = false, length = 120)
    private String visitReason;

    @Column(name = "location", nullable = false, length = 120)
    private String location;

    @Column(name = "privacy_agree", nullable = false)
    private int privacyAgree;

    @Column(name = "visit_date", nullable = false, length = 8)
    private String visitDate;

    @Column(name = "visit_time", nullable = false, length = 8)
    private String visitTime;

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getCltCd() { return cltCd; }

    public void setCltCd(String cltCd) { this.cltCd = cltCd; }

    public String getVisitNm() { return visitNm; }

    public void setVisitNm(String visitNm) { this.visitNm = visitNm; }

    public String getManager() { return manager; }

    public void setManager(String manager) { this.manager = manager; }

    public String getVisitClt() { return visitClt; }

    public void setVisitClt(String visitClt) { this.visitClt = visitClt; }

    public String getVisitReason() { return visitReason; }

    public void setVisitReason(String visitReason) { this.visitReason = visitReason; }

    public String getMobile() { return mobile; }

    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public int getPrivacyAgree() { return privacyAgree; }

    public void setPrivacyAgree(int privacyAgree) { this.privacyAgree = privacyAgree; }

    public String getVisitDate() { return visitDate; }

    public void setVisitDate(String visitDate) { this.visitDate = visitDate; }

    public String getVisitTime() { return visitTime; }

    public void setVisitTime(String visitTime) { this.visitTime = visitTime; }
}
