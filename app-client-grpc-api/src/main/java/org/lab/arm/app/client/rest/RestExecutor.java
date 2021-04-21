package org.lab.arm.app.client.rest;

import com.google.common.collect.Lists;
import org.lab.arm.app.model.OperationAudit;
import org.lab.arm.app.model.ResultBenchmark;
import org.lab.arm.app.model.dto.CreateOperationRequest;
import org.lab.arm.app.model.dto.CreateOperationResponse;
import org.lab.arm.app.support.BaseTaskExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Component
public class RestExecutor extends BaseTaskExecutor {

    private Logger logger = LoggerFactory.getLogger(RestExecutor.class);

    @Value("${app.rest-core-pool-size}")
    private Integer appRestCodePoolSize;

    @Value("${app.millis-time-out-operation}")
    private Integer appMillisTimeOutOperation;

    @Autowired
    private AppProxyRestComponent appProxyRestComponent;

    private final String threadNamePrefix = "RestExecutor";

    @PostConstruct
    private void init() {

        logger.debug("Initializing " + threadNamePrefix);

        initThreadPoolExecutor(appRestCodePoolSize,
                threadNamePrefix.concat("-"));

    }

    @PreDestroy
    public void beanDestroy() {
        try {

            if (getThreadPoolExecutor() != null) {
                getThreadPoolExecutor().shutdown();

                try {
                    if (!getThreadPoolExecutor().awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                        getThreadPoolExecutor().shutdownNow();

                        logger.debug(threadNamePrefix + " - ThreadPoolExecutor.shutdownNow executed success");
                    } else {
                        logger.debug(threadNamePrefix + " - ThreadPoolExecutor.shutdown executed success");
                    }

                } catch (InterruptedException interruptedEx) {
                    getThreadPoolExecutor().shutdownNow();

                    logger.debug(threadNamePrefix
                            + " - ThreadPoolExecutor.shutdownNow executed success (InterruptedException thrown: "
                            + interruptedEx.getMessage() + ")");

                }

            }
        } finally {

        }
    }

    public ResultBenchmark processBenchmark(List<CreateOperationRequest> requests) {
        ResultBenchmark resultBenchmark;
        List<Future<OperationAudit>> futureTasks = null;

        try {

            logger.info("START REST BENCHMARK");

            resultBenchmark = new ResultBenchmark();
            futureTasks = new ArrayList<>();

            List<List<CreateOperationRequest>> partitionsList = Lists.partition(requests, (appRestCodePoolSize / 2));

            for (List<CreateOperationRequest> listRequests :
                    partitionsList) {
                futureTasks.clear();

                for (int i = 0; i < listRequests.size(); i++) {
                    futureTasks.add(this.asyncOperation(this::businessTask, listRequests.get(i)));
                }

                collectTasksResult(futureTasks, resultBenchmark, appMillisTimeOutOperation);
            }

            return resultBenchmark;

        } catch (Exception ex) {
            resultBenchmark = new ResultBenchmark();
            resultBenchmark.setException(ex);
            return resultBenchmark;
        } finally {
            logger.info("END REST BENCHMARK");
        }

    }

    private OperationAudit businessTask(
            CreateOperationRequest param) {
        OperationAudit operationAudit = null;
        try {

            operationAudit = OperationAudit.start("REST", param.getTransactionId());

            RequestRestModel requestRestModel = new RequestRestModel();
            requestRestModel.setId(0);
            requestRestModel.setName(param.getName());
            requestRestModel.setEmail(param.getEmail());
            requestRestModel.setPhones(param.getPhones());

            ResponseEntity<CreateOperationResponse> response = appProxyRestComponent.createPerson(requestRestModel);
            operationAudit.finish();
            operationAudit.setResponse(response);

            return operationAudit;

        } catch (Exception ex) {
            logger.error("ERROR EN GrpcExecutor.businessTask: " + ex.getMessage());
            operationAudit = OperationAudit.start();
            operationAudit.setHasError(true);
            operationAudit.setDescription(ex.getMessage());
            operationAudit.finish();
            return operationAudit;

        } finally {
            logOperationTime(operationAudit);
        }

    }


    private void collectTasksResult(
            List<Future<OperationAudit>> futureTasks,
            ResultBenchmark resultRef, Integer millisTimeOutOperation) {
        if (futureTasks != null) {
            for (Future<OperationAudit> futureItem : futureTasks) {
                OperationAudit resultFuture = null;
                try {
                    resultFuture = futureItem.get(millisTimeOutOperation, TimeUnit.MILLISECONDS);
                    resultRef.addOperationTime(resultFuture);
                } catch (Exception ex) {
                    logger.error("[GrpcExecutor] - Error procesamiento de tarea", ex);
                    OperationAudit operationAudit = OperationAudit.start();
                    operationAudit.setHasError(true);
                    operationAudit.setDescription(ex.getMessage());
                    resultRef.addOperationTime(operationAudit);
                }
            }
        }

    }

}
