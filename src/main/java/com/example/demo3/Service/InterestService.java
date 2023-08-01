package com.example.demo3.Service;

import com.example.demo3.DAO.InterestRepo;
import com.example.demo3.Models.Interest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class InterestService {
    @Autowired
    InterestRepo interestRepoObj;


    public List<Interest> getInterests() {
        return interestRepoObj.findAll();
    }


    public Optional<Interest> getByid(Long interestId) {
        Optional<Interest> interest=interestRepoObj.findById(interestId);
        return interest;
    }

    public void AddInterest(Interest interest) {
        interestRepoObj.save(interest);
    }

    public void removeInterest(Interest interest) {
        interestRepoObj.delete(interest);
    }
}
