package com.example.demo3.externa.DTO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventBriteDTO {


    private String url;
    private EventBriteEventName name;
    private EventBriteEventName description;
    private EventBriteEventStartAndEndTime start;
    private EventBriteEventStartAndEndTime end;


    private class EventBriteEventName {
        private String text;
        private String html;

        @Override
        public String toString() {
            return "EventBriteEventName{" +
                    "text='" + text + '\'' +
                    ", html='" + html + '\'' +
                    '}';
        }
    }

    private class EventBriteEventStartAndEndTime {
        private Date local;
        private Date utc;
        private String timezone;

        @Override
        public String toString() {
            return "EventBriteEventStartAndEndTime{" +
                    "local=" + local +
                    ", utc=" + utc +
                    ", timezone='" + timezone + '\'' +
                    '}';
        }
    }

}
