package org.lab.arm.app.service;

import org.lab.arm.app.client.grpc.GrpcExecutor;
import org.lab.arm.app.client.rest.RestExecutor;
import org.lab.arm.app.model.ResultBenchmark;
import org.lab.arm.app.model.bo.PhoneNumberBo;
import org.lab.arm.app.model.bo.PhoneType;
import org.lab.arm.app.model.dto.BenchmarkResumeResponse;
import org.lab.arm.app.model.dto.CreateOperationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AppService {

    private Logger logger = LoggerFactory.getLogger(GrpcExecutor.class);

    private List<CreateOperationRequest> requests;

    @Value("${app.count-operations-benchmark}")
    private Integer appCountOperationsBenchmarck;


    @Value("${app.path.csv}")
    private String appPathCSV;

    @Autowired
    private RestExecutor restExecutor;

    @Autowired
    private GrpcExecutor gprcExecutor;

    @PostConstruct
    private void init() {

        requests = new ArrayList<>();
        for (int i = 0; i < appCountOperationsBenchmarck; i++) {
            List<PhoneNumberBo> phoneNumberBoList = new ArrayList<>();
            phoneNumberBoList.add(new PhoneNumberBo("351854555", PhoneType.HOME));
            phoneNumberBoList.add(new PhoneNumberBo("351854545", PhoneType.MOBILE));
            phoneNumberBoList.add(new PhoneNumberBo("351854530", PhoneType.WORK));

            CreateOperationRequest request = new CreateOperationRequest(i, 0, String.valueOf(System.currentTimeMillis()),
                    String.valueOf(System.currentTimeMillis()), phoneNumberBoList);

            requests.add(request);
        }
    }

    public BenchmarkResumeResponse restBenchmark() {
        BenchmarkResumeResponse benchmarkResumeResponse = new BenchmarkResumeResponse();
        ResultBenchmark resultBenchmark = restExecutor.processBenchmark(requests);
        benchmarkResumeResponse.finish();
        benchmarkResumeResponse.setDescription("REST-BENCHMARK");
        processResultBenchmark(resultBenchmark, benchmarkResumeResponse);
        createCSVFile(resultBenchmark, "RestBenchmark");

        return benchmarkResumeResponse;
    }

    public BenchmarkResumeResponse grpcBenchmark() {
        BenchmarkResumeResponse benchmarkResumeResponse = new BenchmarkResumeResponse();
        ResultBenchmark resultBenchmark = gprcExecutor.processBenchmark(requests);
        benchmarkResumeResponse.finish();
        benchmarkResumeResponse.setDescription("GRPC-BENCHMARK");
        processResultBenchmark(resultBenchmark, benchmarkResumeResponse);
        createCSVFile(resultBenchmark, "GrpcBenchmark");

        return benchmarkResumeResponse;
    }

    private void processResultBenchmark(ResultBenchmark resultBenchmark, BenchmarkResumeResponse benchmarkResumeResponse) {

        benchmarkResumeResponse.setTotalRequests(resultBenchmark.totalOperations());
        benchmarkResumeResponse.setErrorRequests(resultBenchmark.errorOperations());
        benchmarkResumeResponse.setSuccessRequests(resultBenchmark.successOperations());

        if (resultBenchmark.getException() != null) {
            benchmarkResumeResponse.setHasError(true);
            benchmarkResumeResponse.setMessage(resultBenchmark.getException().getMessage());
            return;
        }

        if (resultBenchmark.allOperationsWithError()) {
            benchmarkResumeResponse.setHasError(true);
            benchmarkResumeResponse.setMessage("GENERAL ERROR IN ALL OPERATIONS");
            return;
        }

        benchmarkResumeResponse.setAvgMillisTime(resultBenchmark.avgTimeResume());
        benchmarkResumeResponse.setMinMillisTime(resultBenchmark.minTimeResume());
        benchmarkResumeResponse.setMaxMillisTime(resultBenchmark.maxTimeResume());
        benchmarkResumeResponse.setStandardDeviationMillisTime(resultBenchmark.sdTimeResume());
    }

    private void createCSVFile(ResultBenchmark resultBenchmark, String name) {
        try {
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss");
            String strDate = dateFormat.format(date);

            String HEADERS = "transactionId,description,started,finished,duration";
            File csvOutputFile = new File(appPathCSV + name + "-" + resultBenchmark.getOperations().size() + "-" + strDate + ".csv");
            try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
                pw.println(HEADERS);

                resultBenchmark.getOperations().stream()
                        .map(x -> x.toCSVLine())
                        .forEach(pw::println);
            }

        } catch (Exception ex) {
            logger.error("Error at createCSVFile", ex);
        }
    }

}
