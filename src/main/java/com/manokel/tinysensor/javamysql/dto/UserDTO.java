package com.manokel.tinysensor.javamysql.dto;

public class UserDTO {
    private int userId;
    private String lastname;
    private String firstname;
    private String email;
    private String postcode;

    public UserDTO() {
    }

    public UserDTO(int id, String lastname, String firstname, String email, String postcode) {
        this.userId = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.postcode = postcode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

}
