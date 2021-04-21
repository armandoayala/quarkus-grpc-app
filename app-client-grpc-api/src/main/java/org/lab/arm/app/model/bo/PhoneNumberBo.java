package org.lab.arm.app.model.bo;

public class PhoneNumberBo {
    private String number;
    private PhoneType type;

    public PhoneNumberBo() {
    }

    public PhoneNumberBo(String number, PhoneType type) {
        this.number = number;
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }
}

