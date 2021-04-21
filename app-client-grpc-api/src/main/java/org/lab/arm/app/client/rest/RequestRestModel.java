package org.lab.arm.app.client.rest;

import org.lab.arm.app.model.bo.PhoneNumberBo;

import java.util.List;

public class RequestRestModel {
    private long id;
    private String name;
    private String email;
    private List<PhoneNumberBo> phones;

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
}
