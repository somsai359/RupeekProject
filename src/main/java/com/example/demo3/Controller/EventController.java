package com.example.demo3.Controller;

import com.example.demo3.Models.Event;
import com.example.demo3.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
public class EventController {
    @Autowired
    EventService eventServiceObj;

    @GetMapping(value = "/events")
    public List<Event> getEvents(){
        return eventServiceObj.getEvent();
    }

    @PostMapping(value = "/add/event")
    public ResponseEntity<Object> addEvent(@RequestBody Event event){
        return eventServiceObj.addevent(event);
    }
     @GetMapping(value = "/event/{city_name}")
     public List<Event>getEventByCity(@PathVariable String city_name){
        List<Event>events= eventServiceObj.findByCity(city_name);
        return events;
     }

    @GetMapping(value = "/events/{interest}")
    public List<Event>getEventByInt(@PathVariable String interest) {
        List<Event> events = eventServiceObj.findByInt(interest);
        return events;
    }

    @GetMapping(value = "/event/{city}/{interest}")
    public List<Event>getEventInCityAndInterest(@PathVariable String city,@PathVariable String interest){
        return eventServiceObj.getAllEventInCityAndInterest(city,interest);
    }

    @PutMapping (value = "/event/{eventName}/{interest}")
    public ResponseEntity<Object>postEventInCityAndInterest(@PathVariable String eventName,@PathVariable String interest){
        return eventServiceObj.postAllEventInCityAndInterest(eventName,interest);
    }

}
