package com.example.demo3.Service;

import com.example.demo3.DAO.Eventrepo;
import com.example.demo3.DAO.InterestRepo;
import com.example.demo3.DAO.UserRepo;
import com.example.demo3.Models.Event;
import com.example.demo3.Models.Interest;
import com.example.demo3.Models.SignIn;
import com.example.demo3.Models.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class UserService {

    @Autowired
    UserRepo userRepoObj;

    @Autowired
    Eventrepo eventrepoobj;

    @Autowired
    InterestRepo interestRepoObj;
    public ResponseEntity<Object> addUser(Users user) {
        List<Users> all_usr = getAllUser();
        for(Users u : all_usr)
        {
            if(u.getEmail().equals(user.getEmail())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Failed: email id already exist\"}");
            }

        }
        userRepoObj.save(user);
        return ResponseEntity.ok(user);
    }

    Long random_id = 0L;
    public void addUserEnc(Users user) {
        String password = user.getPassword();
        random_id++;
        user.setId(String.valueOf(random_id));
        user.setPassword(user.getPassword());
        userRepoObj.save(user);
    }
    public String PasswordEncryption(String password)
    {
        String encryptedpassword = null;
        try
        {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes());
            byte[] bytes = m.digest();
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            encryptedpassword = s.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return encryptedpassword;
    }
    public ResponseEntity<Object> Authenticate(SignIn signInDetails) {
        String password = signInDetails.getPassword();
        String email = signInDetails.getEmail();

        if (password == null) {
            return ResponseEntity.badRequest().body("{\"error\": \"Login Failed: Password cannot be null\"}");
        }

        String user_password = FindUserByEmail(email);
        Users heyUser = FindUserDetailsByEmail(email);
        Users returnUser = new Users();
        returnUser.setFirstName(heyUser.getFirstName());
        returnUser.setEmail(heyUser.getEmail());
        returnUser.setLastName(heyUser.getEmail());
        returnUser.setId(heyUser.getId());
        returnUser.setInterest(heyUser.getInterest());
        returnUser.setRegEvents(heyUser.getRegEvents());

        if (!user_password.equals("null")) {
            if (password.equals(user_password)) {
                return ResponseEntity.ok(returnUser);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Login Failed: Invalid email or password\"}");
    }


    public String FindUserByEmail(String email)
    {
        List<Users> all_usr = getAllUser();
        for(Users u : all_usr)
        {
            if(u.getEmail().equals(email)) {
                return u.getPassword();

            }

        }
        return "null";
    }

    public Users FindUserDetailsByEmail(String email)
    {
        List<Users> all_usr = getAllUser();
        for(Users u : all_usr)
        {
            if(u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }
    public Users getUserByID(String userid) {


        List<Users> all_usr = getAllUser();
        for(Users u : all_usr)
        {
            if(u.getId().equals(userid)) {
                return u;
            }

        }
        return null;
    }

    public List<Users> getAllUser() {
        return userRepoObj.findAll();
    }


    public void deleteUserByID(String userid) {

        List<Users> all_usr = getAllUser();
        for(Users u : all_usr)
        {
            if(u.getId().equals(userid)) {
                userRepoObj.delete(u);
            }
        }
    }


    public String Addinterest(String userid,String interestId) {
        Users user= getUserByID(userid);
        if(user!=null){
            Users users=user;
            List<String> list=users.getInterest();

            if (list!=null){
                list.add(interestId);
                users.setInterest(list);
                userRepoObj.save(users);

            }
            else {
                List<String> list1=new ArrayList<>();
                list1.add(interestId);
                users.setInterest(list1);
                userRepoObj.save(users);
            }
        }
        return "interest added successfully";
    }

    public ResponseEntity<Object> AddEvent(String userid, String event) {
        Users user= getUserByID(userid);
        if(user!=null){
            Users users=user;
            List<String> list=users.getRegEvents();
            if (list!=null){
                if(list.contains(event)){
                    return ResponseEntity.ok(list);
                }
                list.add(event);
                users.setRegEvents(list);
                userRepoObj.save(users);
                return ResponseEntity.ok(list);
            }
            else {
                List<String> list1=new ArrayList<>();
                list1.add(event);
                users.setRegEvents(list1);
                System.out.println(users.getRegEvents());
                userRepoObj.save(users);
                return ResponseEntity.ok("Added"+list1);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist");
    }

    public List<Event> getRegEvents(String userid) {
        Users user= getUserByID(userid);
        List<Event> events=new ArrayList<>();
        if(user!=null) {
            Users users = user;
            List<String> regievents = users.getRegEvents();
            if (regievents != null) {
                for (String i : regievents) {
                    Event event=eventrepoobj.findByName(i);
                    if (event!=null){
                        events.add(event);
                    }
                }
            }
        }
        return events;
    }

    public List<Interest> getInterests(String userid) {
        Users user= getUserByID(userid);
        List<Interest>interests=new ArrayList<>();
        if(user!=null){
            Users users=user;
            List<String>interest=users.getInterest();
            if(interests!=null){
                for(String i: interest){
                    Interest inter=interestRepoObj.findByInterestName(i);
                    if(inter!=null){
                        interests.add(inter);
                    }
                }
            }

        }
        return interests;
    }

//    public List<Interest> delUserInterests(String userid, String inputInterest) {
//        Users user= getUserByID(userid);
//        List<Interest>interests=new ArrayList<>();
//        if(user!=null){
//            List<String>interest=user.getInterest();
//
//            if(interests!=null){
//                for(String i: interest){
//                    //Interest inter=interestRepoObj.findByInterestName(i);
//                    //Interest userInt = interestRepoObj.findByInterestName(inputInterest);
//                    System.out.println(i + " not = " + inputInterest);
//                    if(Objects.equals(i, inputInterest)){
//                        //interests.add(inter);
//                        System.out.println(i + " here = " + inputInterest);
//                        removeInterest();
//                        interestRepoObj.removeInterest
//                        interests.removeInterest(i);
//                    }
//                }
//            }
//
//        }
//        return interests;
//    }
}