package org.lab.arm.quarkus.api.service;

import org.lab.arm.quarkus.api.dto.CreatePersonRequest;
import org.lab.arm.quarkus.api.dto.CreatePersonResponse;
import org.lab.arm.quarkus.api.model.PersonEntity;
import org.lab.arm.quarkus.api.model.PhoneNumber;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AppService {

    public CreatePersonResponse create(CreatePersonRequest request) {
        PersonEntity person = new PersonEntity();
        if (!(request.getId() > 0)) {
            person.setId(System.currentTimeMillis());
        }

        person.setCreatedAt(System.currentTimeMillis());
        person.setEmail(request.getEmail());
        person.setName(request.getName());

        if (request.getPhones() != null && !request.getPhones().isEmpty()) {
            for (PhoneNumber phoneNumber :
                    request.getPhones()) {
                person.getPhones().add(phoneNumber);
            }
        }

        CreatePersonResponse createPersonResponse = new CreatePersonResponse();
        createPersonResponse.setPerson(person);
        createPersonResponse.setSuccess(true);
        createPersonResponse.setMessage("Success");

        return createPersonResponse;
    }

}
