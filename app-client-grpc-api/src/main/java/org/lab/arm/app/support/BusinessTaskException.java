package org.lab.arm.app.support;

public class BusinessTaskException extends RuntimeException {
    public BusinessTaskException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
