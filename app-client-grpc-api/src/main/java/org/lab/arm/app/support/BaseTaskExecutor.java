package org.lab.arm.app.support;


import org.lab.arm.app.model.OperationAudit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public abstract class BaseTaskExecutor {
    private Logger logger = LoggerFactory.getLogger(BaseTaskExecutor.class);
    private ThreadPoolExecutor threadPoolExecutor;

    public void initThreadPoolExecutor(Integer corePoolSize, String threadNamePrefix) {
        Integer queueSize = (corePoolSize == 1 ? 1 : (corePoolSize / 2));

        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize.intValue(),
                corePoolSize.intValue(),
                1000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(queueSize),
                this.buildThreadFactory(threadNamePrefix));
    }

    private ThreadFactory buildThreadFactory(String threadNamePrefix) {

        return new CustomizableThreadFactory(threadNamePrefix);

    }

    public <T, R> Future<R> asyncOperation(Function<T, R> operation,
                                           T param) {
        try {
            Future<R> future = this.threadPoolExecutor.submit(() -> operation.apply(param));
            return future;
        } catch (RejectedExecutionException rejectEx) {
            logger.error("Task rejected", rejectEx);
            throw rejectEx;
        }
    }

    public ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }

    public void logOperationTime(OperationAudit operationAudit) {
        if (operationAudit != null) {
            logger.info(operationAudit.toString());
        } else {
            logger.info("operationAudit IS NULL");
        }
    }

}

