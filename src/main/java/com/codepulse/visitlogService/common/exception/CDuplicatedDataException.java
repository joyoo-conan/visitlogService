package com.codepulse.visitlogService.common.exception;

public class CDuplicatedDataException extends RuntimeException {

    public CDuplicatedDataException() { super(); }

    public CDuplicatedDataException(String message) { super(message); }

    public CDuplicatedDataException(String message, Throwable cause) { super(message, cause); }
}
