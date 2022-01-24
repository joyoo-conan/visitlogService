package com.codepulse.visitlogService.common.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum CommonResponse {
    SUCCESS(0, "succeeded"), FAIL(-1, "failed");

    private int code;
    private String msg;
}
