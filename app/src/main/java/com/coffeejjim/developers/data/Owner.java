package com.coffeejjim.developers.data;

public class Owner implements java.io.Serializable {
    private static final long serialVersionUID = -8349598148610051438L;
    private String ownerLoginId;
    private String password;
    private String ownerName;
    private String ownerPhoneNumber;
    private String ownerEmail;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerLoginId() {
        return ownerLoginId;
    }

    public void setOwnerLoginId(String ownerLoginId) {
        this.ownerLoginId = ownerLoginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
