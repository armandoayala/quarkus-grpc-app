package org.lab.arm.quarkus;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.lab.arm.quarkus.dto.CreatePersonDTO;
import org.lab.arm.quarkus.grpc.GrpcServiceClient;
import org.lab.arm.quarkus.grpc.model.CreatePersonRequest;
import org.lab.arm.quarkus.grpc.model.CreatePersonResponse;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class AppProxyGrpcService {

    @ConfigProperty(name = "grpc.service.host")
    private String gprcServiceHost;

    @ConfigProperty(name = "grpc.service.port")
    private String gprcServicePort;

    private static final Logger logger = Logger.getLogger(AppProxyGrpcService.class.getName());

    private GrpcServiceClient grpcServiceClient;

    @PostConstruct
    public void InitAppProxyGrpcService() {

        grpcServiceClient = new GrpcServiceClient(gprcServiceHost, Integer.valueOf(gprcServicePort));
        logger.info("GRPC SERVICE CLIENT STARTED");
    }

    public CreatePersonResponse createPerson(CreatePersonDTO createPersonDTO) {
        CreatePersonRequest.Builder createPersonRequestBuilder = CreatePersonRequest.newBuilder();

        createPersonRequestBuilder.setName(createPersonDTO.getName());
        createPersonRequestBuilder.setEmail(createPersonDTO.getEmail());

        CreatePersonResponse createPersonResponse = grpcServiceClient.getPersonServiceBlockingStub().create(createPersonRequestBuilder.build());
        return createPersonResponse;
    }

}
