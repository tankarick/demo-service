package com.hdbank.demo.exception;

import lombok.Getter;

@Getter
public enum DemoResult {

    SUCCESS(0, "Success"),
    NOT_FOUND(1, "Not found"),
    UN_KNOWN(-1, "UnKnown"),
    ;

    private int code;
    private String message;

    DemoResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public DemoResult findByCode(int code) {
        for (DemoResult result : values()) {
            if (result.getCode() == code) {
                return result;
            }
        }
        return UN_KNOWN;
    }
}
