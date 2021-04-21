package org.lab.arm.quarkus.api.dto;

import org.lab.arm.quarkus.api.model.PhoneNumber;

import java.util.List;

public class CreatePersonRequest {
    private long id;
    private String name;
    private String email;
    private List<PhoneNumber> phones;

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

    public List<PhoneNumber> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneNumber> phones) {
        this.phones = phones;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
