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
@Table(name = "tb_client_mst")
@Entity
public class Client {
    @Id
    @Column(name = "clt_cd", nullable = false, unique = true, length = 6)
    private String cltCd;

    @Column(name = "clt_nm", nullable = false, length = 250)
    private String cltNm;

    @Column(name = "ceo", nullable = false, length = 120)
    private String ceo;

    @Column(name = "tel", nullable = false, length = 45)
    private String tel;

    @Column(name = "email", nullable = false, length = 200)
    private String email;

    @Column(name = "biz_cd", nullable = false, length = 20)
    private String bizCd;

    @Column(name = "use_yn", nullable = false)
    private int useYn;

    public String getCltCd() { return cltCd; }

    public void setCltCd(String cltCd) { this.cltCd = cltCd; }

    public String getCltNm() { return cltNm; }

    public void setCltNm(String cltNm) { this.cltNm = cltNm; }

    public String getCeo() { return ceo; }

    public void setCeo(String ceo) { this.ceo = ceo; }

    public String getTel() { return tel; }

    public void setTel(String tel) { this.tel = tel; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getBizCd() { return bizCd; }

    public void setBizCd(String bizCd) { this.bizCd = bizCd; }

    public int getUseYn() { return useYn; }

    public void setUseYn(int useYn) { this.useYn = useYn; }
}
