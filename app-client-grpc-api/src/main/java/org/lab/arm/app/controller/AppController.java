package org.lab.arm.app.controller;

import org.lab.arm.app.model.dto.BenchmarkResumeResponse;
import org.lab.arm.app.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-benchmark")
public class AppController {

    @Autowired
    private AppService appService;

    @GetMapping("/rest")
    public ResponseEntity<BenchmarkResumeResponse> restBenchmark() {

        BenchmarkResumeResponse benchmarkResumeResponse;
        try {
            benchmarkResumeResponse = appService.restBenchmark();
            return ResponseEntity.ok(benchmarkResumeResponse);
        } catch (Exception ex) {
            ex.printStackTrace();
            benchmarkResumeResponse = new BenchmarkResumeResponse();
            benchmarkResumeResponse.setHasError(true);
            benchmarkResumeResponse.setMessage(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(benchmarkResumeResponse);
        }

    }

    @GetMapping("/grpc")
    public ResponseEntity<BenchmarkResumeResponse> grpcBenchmark() {

        BenchmarkResumeResponse benchmarkResumeResponse;
        try {
            benchmarkResumeResponse = appService.grpcBenchmark();
            return ResponseEntity.ok(benchmarkResumeResponse);
        } catch (Exception ex) {
            ex.printStackTrace();
            benchmarkResumeResponse = new BenchmarkResumeResponse();
            benchmarkResumeResponse.setHasError(true);
            benchmarkResumeResponse.setMessage(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(benchmarkResumeResponse);
        }

    }

}
