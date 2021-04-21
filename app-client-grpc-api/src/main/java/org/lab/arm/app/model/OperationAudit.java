package org.lab.arm.app.model;

import java.time.Duration;
import java.time.Instant;

public class OperationAudit {
    private String transactionId;
    private String description;
    private Instant startInstant;
    private Instant finishInstant;
    private boolean hasError;
    private Object response;

    private OperationAudit() {

    }

    public static OperationAudit start() {
        OperationAudit operationAudit = new OperationAudit();
        operationAudit.setStartInstant(Instant.now());
        return operationAudit;
    }

    public static OperationAudit start(String description, String transactionId) {
        OperationAudit operationAudit = start();
        operationAudit.setDescription(description);
        operationAudit.setTransactionId(transactionId);
        return operationAudit;
    }

    public static OperationAudit start(String description, long transactionId) {
        OperationAudit operationAudit = start();
        operationAudit.setDescription(description);
        operationAudit.setTransactionId(String.valueOf(transactionId));
        return operationAudit;
    }

    public long finish() {
        this.setFinishInstant(Instant.now());
        return duration();
    }

    public long duration() {
        if (finishInstant == null) {
            this.setFinishInstant(Instant.now());
        }

        return Duration.between(startInstant, finishInstant).toMillis();
    }

    public Instant getStartInstant() {
        return startInstant;
    }

    private void setStartInstant(Instant startInstant) {
        this.startInstant = startInstant;
    }

    public Instant getFinishInstant() {
        return finishInstant;
    }

    private void setFinishInstant(Instant finishInstant) {
        this.finishInstant = finishInstant;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }


    public boolean getHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OperationAudit{");
        sb.append("transactionId='").append(transactionId).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", startInstant=").append(startInstant);
        sb.append(", finishInstant=").append(finishInstant);
        sb.append(", duration=").append(duration());
        sb.append(", hasError=").append(hasError);
        sb.append('}');
        return sb.toString();
    }

    public String toCSVLine()
    {
        return this.transactionId+","+this.description+","+this.startInstant.toEpochMilli()+","+this.finishInstant.toEpochMilli()+","+this.duration();
    }
}
