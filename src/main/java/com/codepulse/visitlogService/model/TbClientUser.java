package com.codepulse.visitlogService.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@ToString(callSuper = true)
@DynamicInsert
@DynamicUpdate
@Entity
@Setter
@Getter
@Builder
@Table(name = "tb_client_user")
@NoArgsConstructor
@AllArgsConstructor
public class TbClientUser {
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
