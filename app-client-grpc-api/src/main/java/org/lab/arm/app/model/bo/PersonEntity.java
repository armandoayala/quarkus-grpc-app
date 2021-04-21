package org.lab.arm.app.model.bo;

import java.util.ArrayList;
import java.util.List;

public class PersonEntity {

    private long id;
    private String name;
    private String email;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;
    private List<PhoneNumberBo> phones;

    public PersonEntity() {
        this.phones = new ArrayList<>();
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

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(long deletedAt) {
        this.deletedAt = deletedAt;
    }

    public List<PhoneNumberBo> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneNumberBo> phones) {
        this.phones = phones;
    }
}

