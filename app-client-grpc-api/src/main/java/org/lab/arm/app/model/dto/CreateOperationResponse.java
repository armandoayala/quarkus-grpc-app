package org.lab.arm.app.model.dto;

import org.lab.arm.app.model.bo.PersonEntity;

public class CreateOperationResponse {
    private PersonEntity person;
    private Boolean success;
    private String message;

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
