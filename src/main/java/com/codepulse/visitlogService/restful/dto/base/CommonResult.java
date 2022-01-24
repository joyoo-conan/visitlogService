package com.codepulse.visitlogService.restful.dto.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResult {
    
    //@ApiModelProperty(value = "응답 성공여부 (true/false)")
    private boolean success;

    //@ApiModelProperty(value = "응답 코드 (비정상 = -1, 그 외 0 이상)")
    private int code;

    //@ApiModelProperty(value = "응답 메시지 (비정상인 경우 적용)")
    private String msg;

    //@ApiModelProperty(value = "응답 데이터")
    private String data;

    //@ApiModelProperty(value = "응답시간(yyyyMMddhhmmssff")
    private String time;
}
