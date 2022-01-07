package com.codepulse.visitlogService.model;

import lombok.Getter;
import lombok.Setter;

public class Response {
    @Getter
    @Setter
    private int val;

    @Getter
    @Setter
    private String msg;

    @Getter
    @Setter
    private String time;

    @Getter
    @Setter
    private String data;
}
