package com.example.plexora;

import jakarta.persistence.ElementCollection;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class UserWrapper {
    private String email;
    private String name;
    private Date dob;
    private String phone;
    private Long userId;
    @ElementCollection
    private List<String> location = new ArrayList<>();

    public UserWrapper() {
    }

    public UserWrapper(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.dob = user.getDob();
        this.phone = user.getPhone();
        this.userId = user.getUserId();
    }

    public UserWrapper(String email, String name, Date dob, String phone, Long userId) {
        this.email = email;
        this.name = name;
        this.dob = dob;
        this.phone = phone;

        this.userId = userId;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
