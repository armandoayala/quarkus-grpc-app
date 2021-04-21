package org.lab.arm.app.model.dto;

import org.lab.arm.app.model.bo.PhoneNumberBo;

import java.util.List;

public class CreateOperationRequest {
    private long transactionId;
    private long id;
    private String name;
    private String email;
    private List<PhoneNumberBo> phones;

    public CreateOperationRequest() {
    }

    public CreateOperationRequest(long transactionId, long id, String name, String email, List<PhoneNumberBo> phones) {
        this.transactionId = transactionId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.phones = phones;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PhoneNumberBo> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneNumberBo> phones) {
        this.phones = phones;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }
}
