package com.education.analytics.model.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Activity {

    private final String time;
    private final String eventContext;
    private final String eventName;
    private final String component;
    private final String description;

    @JsonCreator
    public Activity( //
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

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("eventContext")
    public String getEventContext() {
        return eventContext;
    }

    @JsonProperty("eventName")
    public String getEventName () {
        return  eventName;
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
        Activity activity = (Activity) o;
        return Objects.equals(time, activity.time) &&
                Objects.equals(eventContext, activity.eventContext) &&
                Objects.equals(eventName, activity.eventName) &&
                Objects.equals(component, activity.component) &&
                Objects.equals(description, activity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, eventContext, component, description, eventName);
    }
}
