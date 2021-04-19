package com.education.analytics.model.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

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

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("eventContext")
    public String getEventContext() {
        return eventContext;
    }

    @JsonProperty("component")
    public String getComponent() {
        return component;
    }

    @JsonProperty("description")
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
                Objects.equals(component, courseLog.component) &&
                Objects.equals(description, courseLog.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, eventContext, component, description);
    }
}
