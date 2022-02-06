package com.codepulse.visitlogService.common.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomMessage {

    SUCCESS (0, "success"),
    FAIL (-1, "fail"),

    /**
     * Client error Message
     */
    CLIENT_DUPLICATED (101, "error.client_duplicated")
    ;

    private int code;
    private String msg;
}
