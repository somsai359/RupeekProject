package com.example.demo3.DAO;

import com.example.demo3.Models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<Users,Long> {


}
