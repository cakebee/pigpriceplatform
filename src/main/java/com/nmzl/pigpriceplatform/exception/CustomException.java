package com.nmzl.pigpriceplatform.exception;

@SuppressWarnings("uncheck")
public class CustomException extends RuntimeException {
    int code;

    public CustomException(int code) {
        this.code = code;
    }
}
