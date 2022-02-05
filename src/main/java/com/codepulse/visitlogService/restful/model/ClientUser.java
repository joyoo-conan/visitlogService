package com.codepulse.visitlogService.restful.model;

import com.codepulse.visitlogService.restful.model.base.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_client_user")
@DynamicInsert
@DynamicUpdate
@Schema(description = "Client 사용자 테이블")
public class ClientUser extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Schema(description = "ClientUser Id")
    private long id;

    @Column(name = "user_id", nullable = false, unique = true, length = 50)
    @Schema(description = "User 아이디")
    private String userId;

    @Column(name = "user_pw", nullable = false, unique = true, length = 1024)
    @Schema(description = "User 비밀번호")
    private String userPw;

    @Column(name = "clt_cd", nullable = false, length = 6)
    @Schema(description = "고객사 코드")
    private String cltCd;

    @Column(name = "email", nullable = false, length = 200)
    @Schema(description = "USER 이메일")
    private String email;

    @Column(name = "level", nullable = false)
    @Schema(description = "USER Level(0:관리자, 1:Manager, 2:Guest")
    private Integer level;

    @Column(name = "use_yn", nullable = false, columnDefinition = "tinyint")
    @Schema(description = "사용여부")
    private Integer useYn;

    @PrePersist
    public void prePersist() {
        this.useYn = this.useYn == null ? 1 : this.useYn;
    }
}
