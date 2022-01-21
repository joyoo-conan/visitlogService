package com.codepulse.visitlogService.common.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonResponse {
    SUCCESS(0, "succeeded"), FAIL(-1, "failed");

    private int code;
    private String msg;
}
