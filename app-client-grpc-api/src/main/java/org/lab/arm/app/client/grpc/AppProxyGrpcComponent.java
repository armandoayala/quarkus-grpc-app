package org.lab.arm.app.client.grpc;

import org.lab.arm.quarkus.grpc.model.CreatePersonRequest;
import org.lab.arm.quarkus.grpc.model.CreatePersonResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

@Component
public class AppProxyGrpcComponent {

    @Value("${grpc.service.host}")
    private String gprcServiceHost;

    @Value("${grpc.service.port}")
    private String gprcServicePort;

    private static final Logger logger = Logger.getLogger(AppProxyGrpcComponent.class.getName());

    private GrpcServiceClient grpcServiceClient;

    @PostConstruct
    private void InitAppProxyGrpcService() {

        grpcServiceClient = new GrpcServiceClient(gprcServiceHost, Integer.valueOf(gprcServicePort));
        logger.info("GRPC SERVICE CLIENT STARTED");
    }

    public CreatePersonResponse createPerson(CreatePersonRequest createPersonRequest) {
        return grpcServiceClient.getPersonServiceBlockingStub().create(createPersonRequest);
    }

}
