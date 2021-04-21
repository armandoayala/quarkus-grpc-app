package org.lab.arm.quarkus.grpc.service;

import org.lab.arm.quarkus.grpc.model.CreatePersonResponse;
import org.lab.arm.quarkus.grpc.model.PersonEntity;
import org.lab.arm.quarkus.grpc.model.PhoneNumber;

public class PersonServiceImpl extends PersonServiceGrpc.PersonServiceImplBase {

    @Override
    public void create(org.lab.arm.quarkus.grpc.model.CreatePersonRequest request,
                       io.grpc.stub.StreamObserver<org.lab.arm.quarkus.grpc.model.CreatePersonResponse> responseObserver) {

        PersonEntity.Builder personBuilder = PersonEntity.newBuilder();

        if (!(request.getId() > 0)) {
            personBuilder.setId(System.currentTimeMillis());
        }
        personBuilder.setCreatedAt(System.currentTimeMillis());
        personBuilder.setEmail(request.getEmail());
        personBuilder.setName(request.getName());

        if (request.getPhonesList() != null && !request.getPhonesList().isEmpty()) {
            for (PhoneNumber phoneNumber :
                    request.getPhonesList()) {
                personBuilder.addPhones(phoneNumber);
            }
        }

        CreatePersonResponse.Builder createPersonResponseBuilder = CreatePersonResponse.newBuilder();
        createPersonResponseBuilder.setPerson(personBuilder.build());
        createPersonResponseBuilder.setSuccess(true);
        createPersonResponseBuilder.setMessage("Success");

        responseObserver.onNext(createPersonResponseBuilder.build());
        responseObserver.onCompleted();

    }

}
