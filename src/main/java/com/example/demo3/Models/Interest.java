package com.example.demo3.Models;


import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="interests")
public class Interest {

    private long id;
    private String interestName;

    public long getId() {
        return id;
    }

    public Interest() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }
}