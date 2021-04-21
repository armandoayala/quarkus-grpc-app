package org.lab.arm.app.support;

public class ResultTask {
    private BusinessTaskException exception;
    private String error;
    private boolean hasError;

    public ResultTask() {
    }

    public ResultTask(BusinessTaskException exception, String error, boolean hasError) {
        this.exception = exception;
        this.error = error;
        this.hasError = hasError;
    }

    public BusinessTaskException getException() {
        return exception;
    }

    public void setException(BusinessTaskException exception) {
        this.exception = exception;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean getHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }
}
