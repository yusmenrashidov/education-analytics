package com.education.analytics.model.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseLog {

    private final String time;
    private final String eventContext;
    private final String component;
    private final String description;

    @JsonCreator
    public CourseLog( //
                      @JsonProperty("time") String time, //
                      @JsonProperty("eventContext") String eventContext, //
                      @JsonProperty("component") String component, //
                      @JsonProperty("description") String description //
    ) {
        this.time = time;
        this.eventContext = eventContext;
        this.component = component;
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public String getEventContext() {
        return eventContext;
    }

    public String getComponent() {
        return component;
    }

    public String getDescription() {
        return description;
    }
}
