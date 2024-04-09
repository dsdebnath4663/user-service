package com.users.userservice.domain;

public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String avatar;
    private String domain;
    private String  available;

    public User() {

    }

    public User(Long id, String firstName, String lastName, String email, String gender, String avatar, String domain, String  available) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.avatar = avatar;
        this.domain = domain;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String  getAvailable() {
        return available;
    }

    // Ensure available value is a single character
    public void setAvailable(String available) {
        if (available != null && available.length() > 1) {
            throw new IllegalArgumentException("Available value must be a single character ('Y' or 'N').");
        }
        this.available = available;
    }


}
