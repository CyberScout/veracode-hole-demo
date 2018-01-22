package com.notaneye.demo.friendly.web;


import java.util.UUID;


@SuppressWarnings("unused")
public class RegistrationCommand {

    private UUID registrationId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String ssn;
    private String dob;


    public UUID getRegistrationId() {

        return registrationId;
    }


    public void setRegistrationId(UUID registrationId) {

        this.registrationId = registrationId;
    }


    public String getUsername() {

        return username;
    }


    public void setUsername(String username) {

        this.username = username;
    }


    public String getPassword() {

        return password;
    }


    public void setPassword(String password) {

        this.password = password;
    }


    public String getFirstName() {

        return firstName;
    }


    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }


    public String getLastName() {

        return lastName;
    }


    public void setLastName(String lastName) {

        this.lastName = lastName;
    }


    public String getSsn() {

        return ssn;
    }


    public void setSsn(String ssn) {

        this.ssn = ssn;
    }


    public String getDob() {

        return dob;
    }


    public void setDob(String dob) {

        this.dob = dob;
    }

}
