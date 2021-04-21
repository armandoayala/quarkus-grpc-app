package org.lab.arm.app.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.lab.arm.app.model.OperationAudit;

import java.time.LocalDateTime;

public class BenchmarkResumeResponse {
    private String description;
    private double avgMillisTime;
    private double standardDeviationMillisTime;
    private long minMillisTime;
    private long maxMillisTime;
    private int totalRequests;
    private int errorRequests;
    private int successRequests;
    private boolean hasError;
    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime started;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finished;

    private OperationAudit operationAudit;

    public BenchmarkResumeResponse() {
        started = LocalDateTime.now();
        operationAudit = OperationAudit.start();
    }

    public double getAvgMillisTime() {
        return avgMillisTime;
    }

    public void setAvgMillisTime(double avgMillisTime) {
        this.avgMillisTime = avgMillisTime;
    }

    public long getMinMillisTime() {
        return minMillisTime;
    }

    public void setMinMillisTime(long minMillisTime) {
        this.minMillisTime = minMillisTime;
    }

    public long getMaxMillisTime() {
        return maxMillisTime;
    }

    public void setMaxMillisTime(long maxMillisTime) {
        this.maxMillisTime = maxMillisTime;
    }

    public boolean getHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(int totalRequests) {
        this.totalRequests = totalRequests;
    }

    public int getErrorRequests() {
        return errorRequests;
    }

    public void setErrorRequests(int errorRequests) {
        this.errorRequests = errorRequests;
    }

    public int getSuccessRequests() {
        return successRequests;
    }

    public void setSuccessRequests(int successRequests) {
        this.successRequests = successRequests;
    }

    public LocalDateTime getStarted() {
        return started;
    }

    public void setStarted(LocalDateTime started) {
        this.started = started;
    }

    public LocalDateTime getFinished() {
        return finished;
    }

    public void setFinished(LocalDateTime finished) {
        this.finished = finished;
    }

    @JsonProperty("duration")
    public long getDuration() {
        return operationAudit.duration();
    }

    public void finish() {
        this.finished = LocalDateTime.now();
        operationAudit.finish();
    }

    public double getStandardDeviationMillisTime() {
        return standardDeviationMillisTime;
    }

    public void setStandardDeviationMillisTime(double standardDeviationMillisTime) {
        this.standardDeviationMillisTime = standardDeviationMillisTime;
    }
}
