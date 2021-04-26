# quarkus-grpc-app
Grpc demo applications.

## app-client-grpc-api
Spring boot application to execute a load tests on quarkus api and grpc application.
## grpc-app/quarkus-grpc-model
Library with application model generated from proto files.
## grpc-app/quarkus-grpc-service
Library with implementations of the services. This project references quarkus-grpc-model.
## grpc-app/quarkus-grpc-server
Application to start grpc server. This project references quarkus-grpc-service.
## grpc-app/quarkus-grpc-client
Application to invoke the grpc service by quarkus-grpc-server. This project references quarkus-grpc-model.
## grpc-app/quarkus-api
Microservice (REST) developed with quarkus. 
