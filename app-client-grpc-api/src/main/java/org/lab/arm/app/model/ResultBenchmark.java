package org.lab.arm.app.model;

import org.lab.arm.app.support.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.stream.Collectors;


public class ResultBenchmark {

    private List<OperationAudit> operations;
    private Exception exception;

    public ResultBenchmark() {
        operations = new ArrayList<>();
    }

    public void addOperationTime(OperationAudit operationAudit) {
        this.operations.add(operationAudit);
    }

    public List<OperationAudit> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationAudit> operations) {
        this.operations = operations;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public double avgTimeResume() {

        List<OperationAudit> operationAuditsToProcess = getOperationsWithoutError();

        if (operationAuditsToProcess.isEmpty()) {
            return 0;
        }

        OptionalDouble resumeValue = operationAuditsToProcess
                .stream()
                .mapToDouble(a -> a.duration())
                .average();

        return resumeValue.isPresent() ? resumeValue.getAsDouble() : 0;
    }

    public long minTimeResume() {

        List<OperationAudit> operationAuditsToProcess = getOperationsWithoutError();

        if (operationAuditsToProcess.isEmpty()) {
            return 0;
        }

        OptionalLong resumeValue = operationAuditsToProcess
                .stream()
                .mapToLong(a -> a.duration())
                .min();

        return resumeValue.isPresent() ? resumeValue.getAsLong() : 0;
    }

    public long maxTimeResume() {

        List<OperationAudit> operationAuditsToProcess = getOperationsWithoutError();

        if (operationAuditsToProcess.isEmpty()) {
            return 0;
        }

        OptionalLong resumeValue = operationAuditsToProcess
                .stream()
                .mapToLong(a -> a.duration())
                .max();

        return resumeValue.isPresent() ? resumeValue.getAsLong() : 0;
    }

    public double sdTimeResume() {
        List<OperationAudit> operationAuditsToProcess = getOperationsWithoutError();
        if (operationAuditsToProcess.isEmpty()) {
            return 0;
        }

        List<Double> values = operationAuditsToProcess.stream().map(x -> (double) x.duration()).collect(Collectors.toList());
        return Util.calculateSD(values);
    }

    public List<OperationAudit> getOperationsWithoutError() {
        return operations
                .stream()
                .filter(x -> x.getHasError() == false)
                .collect(Collectors.toList());
    }

    public boolean allOperationsWithError() {

        if (operations == null) {
            return false;
        }

        List<OperationAudit> operationAuditsError = operations
                .stream()
                .filter(x -> x.getHasError() == true)
                .collect(Collectors.toList());

        return operationAuditsError.size() == operations.size();
    }

    public int totalOperations() {
        return operations.size();
    }

    public int errorOperations() {
        return operations
                .stream()
                .filter(x -> x.getHasError() == true)
                .collect(Collectors.toList()).size();
    }

    public int successOperations() {
        return operations
                .stream()
                .filter(x -> x.getHasError() == false)
                .collect(Collectors.toList()).size();
    }
}
