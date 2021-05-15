package com.education.analytics.model.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CourseLog {

    private final String time;
    private final String eventContext;
    private final String eventName;
    private final String component;
    private final String description;

    @JsonCreator
    public CourseLog( //
                      @JsonProperty("time") String time, //
                      @JsonProperty("eventContext") String eventContext, //
                      @JsonProperty("component") String component, //
                      @JsonProperty("eventName") String eventName, //
                      @JsonProperty("description") String description //
    ) {
        this.time = time;
        this.eventContext = eventContext;
        this.eventName = eventName;
        this.component = component;
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public String getEventContext() {
        return eventContext;
    }

<<<<<<< Updated upstream
=======
    @JsonProperty("eventName")
    public String getEventName () {
        return  eventName;
    }

    @JsonProperty("component")
>>>>>>> Stashed changes
    public String getComponent() {
        return component;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseLog courseLog = (CourseLog) o;
        return Objects.equals(time, courseLog.time) &&
                Objects.equals(eventContext, courseLog.eventContext) &&
                Objects.equals(eventName, courseLog.eventName) &&
                Objects.equals(component, courseLog.component) &&
                Objects.equals(description, courseLog.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, eventContext, component, description, eventName);
    }
}
