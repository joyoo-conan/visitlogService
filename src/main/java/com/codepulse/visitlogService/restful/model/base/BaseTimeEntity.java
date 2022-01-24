package com.codepulse.visitlogService.restful.model.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   //해당 class 상속시 createDt, modDt를 컬럼으로 인식함
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate //Entity가 생성되어 저장되는 시간 자동 저장
    @Schema(description = "최초 생성일")
    private LocalDateTime createDt;

    @LastModifiedDate //값을 수정할때 시간 자동 저장
    @Schema(description = "최종 생성일")
    private LocalDateTime modDt;
}
