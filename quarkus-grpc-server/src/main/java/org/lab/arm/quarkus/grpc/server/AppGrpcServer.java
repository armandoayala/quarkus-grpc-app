package org.lab.arm.quarkus.grpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.lab.arm.quarkus.grpc.service.PersonServiceImpl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class AppGrpcServer {

    private static final Logger logger = Logger.getLogger(AppGrpcServer.class.getName());

    private static final int DEFAULT_PORT = 50051;

    private Server server = null;

    private void start() throws IOException {

        server = ServerBuilder.forPort(DEFAULT_PORT).addService(new PersonServiceImpl())
                .build().start();

        logger.info("Server started, listening on " + DEFAULT_PORT);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.out.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                    AppGrpcServer.this.stop();
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }
                System.out.println("*** server shut down");
            }
        });
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(10, TimeUnit.SECONDS);
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon
     * threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final AppGrpcServer server = new AppGrpcServer();
        server.start();
        server.blockUntilShutdown();
    }
}
