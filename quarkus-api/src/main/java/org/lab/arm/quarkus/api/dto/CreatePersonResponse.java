package org.lab.arm.quarkus.api.dto;

import org.lab.arm.quarkus.api.model.PersonEntity;

public class CreatePersonResponse {

    private PersonEntity person;
    private Boolean success;
    private String message;

    public CreatePersonResponse() {
    }

    public CreatePersonResponse(PersonEntity person, Boolean success, String message) {
        this.person = person;
        this.success = success;
        this.message = message;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
