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
    private long id;

    @Column(name = "user_id", nullable = false, unique = true, length = 50)
    private String userId;

    @Column(name = "user_pw", nullable = false, unique = true, length = 1024)
    private String userPw;

    @Column(name = "clt_cd", nullable = false, length = 6)
    private String cltCd;

    @Column(name = "email", nullable = false, length = 200)
    private String email;

    @Column(name = "level", nullable = false)
    private int level;

    @Column(name = "use_yn", nullable = false, columnDefinition = "tinyint")
    private int useYn;
}
