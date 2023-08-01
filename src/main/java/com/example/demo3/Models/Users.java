package com.example.demo3.Models;

import com.mongodb.lang.NonNull;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="users")
public class Users {
    public Users() {
    }

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    @NonNull
    private String password;
    //    @DBRef
    private List<String> interest;
    private List<String> regEvents;

    public String getId() {
        return id;
    }


    public void setId(String id) {
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
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public List<String> getInterest() {
        return interest;
    }

    public void setInterest(List<String> interest) {
        this.interest = interest;
    }

    public List<String> getRegEvents() {
        return regEvents;
    }

    public void setRegEvents(List<String> regEvents) {
        this.regEvents = regEvents;
    }
}
