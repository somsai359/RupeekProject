package com.example.demo3.Service;

import com.example.demo3.DAO.Eventrepo;
import com.example.demo3.Models.Event;

import com.example.demo3.Models.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class EventService {


    Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    Eventrepo eventrepoObj;


    public ResponseEntity<Object> addevent(Event event) {
         eventrepoObj.save(event);
         return  ResponseEntity.ok(event);
    }

    public List<Event> getEvent() {
        return eventrepoObj.findAll();
    }



     public List<Event> findByCity(String cityName) {
         List<Event>events=eventrepoObj.findByCityName(cityName);
         return events;
     }

    public List<Event> findByInt(String interest) {
        List<Event> events=eventrepoObj.findAll();
        List<Event> eventList=new ArrayList<>();
        if (events!=null) {
            for (Event i : events) {
                List<String> inter = i.getInterest();
                if (inter.contains(interest)) {
                    eventList.add(i);
                }
            }
        }
        return eventList;
    }

    public List<Event> getAllEventInCityAndInterest(String city, String interest) {
        List<Event> events=eventrepoObj.findByCityName(city);
        List<Event> eventList=new ArrayList<>();
        if (events!=null) {
            for (Event i : events) {
                List<String> inter = i.getInterest();
                if (inter.contains(interest)) {
                    eventList.add(i);
                }
            }
        }
        return eventList;
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public ResponseEntity<Object> postAllEventInCityAndInterest(String eventName, String interest) {

        Event event = getEventByName(eventName);
        if(event!=null){
            Event events = event;
            List<String> list = events.getInterest();
            if(list!=null){
                if(list.contains(interest)){
                    return ResponseEntity.ok(list);
                }
                list.add(interest);
                events.setInterest(list);
                eventrepoObj.save(events);
                return ResponseEntity.ok(list);
            }

        else {
            List<String> list1=new ArrayList<>();
            list1.add(interest);
            events.setInterest(list1);
            //System.out.println(users.getRegEvents());
            eventrepoObj.save(events);
            return ResponseEntity.ok("Added"+list1);
        }
    }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Event does not exist");
    }

    private Event getEventByName(String eventName) {
        List<Event> all_event = getAllEvent();

        for(Event e : all_event){
            System.out.println(e+" eventName " + eventName);
            if(e.getEventName().equals(eventName)){
                return e;
            }
        }
        return null;
//        List<Users> all_usr = getAllUser();
//        for(Users u : all_usr)
//        {
//            if(u.getId().equals(userid)) {
//                return u;
//            }
//
//        }
//        return null;
    }

    private List<Event> getAllEvent() {
        return eventrepoObj.findAll();
    }
}
