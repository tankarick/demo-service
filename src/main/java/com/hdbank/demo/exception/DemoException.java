package com.hdbank.demo.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DemoException extends RuntimeException {

    private int resultCode;
    private String message;

    public DemoException() {
        super();
    }

    public DemoException(String message) {
        super(message);
        this.resultCode = DemoResult.UN_KNOWN.getCode();
        this.message = message;
    }

    public DemoException(String message, Throwable cause) {
        super(message, cause);
        this.resultCode = DemoResult.UN_KNOWN.getCode();
        this.message = message;
    }

    public DemoException(Throwable cause) {
        super(cause);
        this.resultCode = DemoResult.UN_KNOWN.getCode();
        this.message = cause.getMessage();
    }

    public DemoException(DemoResult result) {
        super(result.getMessage());
        this.resultCode = result.getCode();
        this.message = result.getMessage();
    }
}
