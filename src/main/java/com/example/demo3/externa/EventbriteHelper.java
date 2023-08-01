package com.example.demo3.externa;

import com.example.demo3.externa.DTO.EventBriteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;


public class EventbriteHelper {
    @Autowired private RestTemplate restTemplate;
    public EventBriteDTO fetchEventsByOrganization(String organizationId) throws Exception {
        String url = "https://www.eventbriteapi.com/v3/organizations/" + organizationId + "/events/";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorzation", "Bearer ZIJGTAF5X6YGKDB2FNQ2");
        HttpEntity<EventBriteDTO> entity = new HttpEntity<EventBriteDTO>(httpHeaders);
        return restTemplate.exchange(url, HttpMethod.GET, entity, EventBriteDTO.class).getBody();
    }
}
