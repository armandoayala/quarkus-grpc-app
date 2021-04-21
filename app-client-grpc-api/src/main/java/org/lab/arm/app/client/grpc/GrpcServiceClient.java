package org.lab.arm.app.client.grpc;

import io.grpc.ManagedChannel;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;
import org.lab.arm.quarkus.grpc.service.PersonServiceGrpc;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class GrpcServiceClient {

    private static final Logger logger = Logger.getLogger(GrpcServiceClient.class.getName());

    private final ManagedChannel channel;

    private final PersonServiceGrpc.PersonServiceBlockingStub personServiceBlockingStub;

    public GrpcServiceClient(String host, int port) {

        channel = NettyChannelBuilder.forAddress(host, port).negotiationType(NegotiationType.PLAINTEXT).build();
        personServiceBlockingStub = PersonServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public PersonServiceGrpc.PersonServiceBlockingStub getPersonServiceBlockingStub() {
        return personServiceBlockingStub;
    }
}
